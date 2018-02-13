package com.bs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bs.entity.CustomerAccount;
import com.bs.repository.CustomerAccountRepository;

@Service
// @CacheConfig(cacheNames = "users")
public class UserService {

	@Autowired
	private CustomerAccountRepository customerRepo;

	@Cacheable("alluserscache")
	public List<CustomerAccount> getAllUsers() {
		List<CustomerAccount> customers = customerRepo.getAllUsers();
		return customers;
	}

	@Cacheable(cacheNames = "usercache", key = "#root.methodName")
	public CustomerAccount findUser(int id) {
		return customerRepo.findOne(id);
	}

	@Cacheable(cacheNames = "usercache", key = "#id")
	public CustomerAccount findOneUser(int id) {
		return customerRepo.findOneUser(id);
	}

	public Integer saveUser(CustomerAccount customerAccount) {
		return customerRepo.save(customerAccount);
	}

	public void persistUser(CustomerAccount customerAccount) {
		customerRepo.persist(customerAccount);
	}

	@CacheEvict(cacheNames = "alluserscache", allEntries = true)
	public void evictAllUsersCache() {
	}

	@CacheEvict(cacheNames = "usercache", allEntries = true)
	public void evictUserCache() {
	}
}