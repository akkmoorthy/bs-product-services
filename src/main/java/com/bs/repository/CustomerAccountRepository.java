package com.bs.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bs.entity.CustomerAccount;

// This will be AUTO IMPLEMENTED by Spring into a Bean called customerAccountRepository
// CRUD refers Create, Read, Update, Delete
@Repository
@Transactional
public class CustomerAccountRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@PersistenceContext
	private EntityManager em;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<CustomerAccount> getAllUsers() {
		return getSession().createQuery("from CustomerAccount").list();
	}

	public Integer save(CustomerAccount customerAccount) {
		return (Integer) getSession().save(customerAccount);
	}

	public void persist(CustomerAccount customerAccount) {
		getSession().persist(customerAccount);
	}

	public CustomerAccount findOne(int id) {
		// Query query = getSession().createQuery("from CustomerAccount c WHERE id=?");
		// query.setParameter(0, id);
		return (CustomerAccount) getSession().get(CustomerAccount.class, new Integer(id));
	}

	public CustomerAccount findOneUser(int id) {
		return (CustomerAccount) em.find(CustomerAccount.class, new Integer(id));
	}

	public List<CustomerAccount> findAll() {
		return em.createQuery("from CustomerAccount").getResultList();
	}
}