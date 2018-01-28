package com.bs.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.bs.entity.User;

public interface SolrUserRepository extends SolrCrudRepository<User, String> {

	public List<User> findByContactName(String name);

	@Query("id:*?0* OR name:*?0*")
	public Page<User> findByCustomQuery(String searchTerm, Pageable pageable);

	@Query(name = "Product.findByNamedQuery")
	public Page<User> findByNamedQuery(String searchTerm, Pageable pageable);

	public User save(User user);
}