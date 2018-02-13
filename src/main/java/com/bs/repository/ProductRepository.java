package com.bs.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bs.entity.Movie;
import com.bs.entity.Theatre;

// This will be AUTO IMPLEMENTED by Spring into a Bean called customerAccountRepository
// CRUD refers Create, Read, Update, Delete
@Repository
@Transactional
public class ProductRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@PersistenceContext
	private EntityManager em;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<Theatre> getAllTheatres(Integer cityId) {
		org.hibernate.Query query = getSession()
				.createQuery("from Theatre t WHERE t.city.id = ? ORDER BY t.theatreName DESC");
		query.setParameter(0, cityId);
		query.setFirstResult(0);
		query.setMaxResults(10);

		return query.list();
	}

	public List<Movie> findAllMovies() {
		return em.createQuery("from Movie m ORDER BY m.title ASC").getResultList();
	}
}