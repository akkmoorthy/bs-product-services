package com.bs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.entity.CustomerAccount;
import com.bs.repository.CustomerAccountRepository;

@Service
public class UserService {

	@Autowired
	private CustomerAccountRepository customerRepo;

	public List<CustomerAccount> getAllUsers() {
		List<CustomerAccount> customers = customerRepo.getAllUsers();
		return customers;
	}
	
	public CustomerAccount findUser(int id) {
		return customerRepo.findOne(id);
	}
	
	public CustomerAccount findOneUser(int id) {
		return customerRepo.findOneUser(id);
	}

	public Integer saveUser(CustomerAccount customerAccount) {
		return customerRepo.save(customerAccount);
	}
	
	public void persistUser(CustomerAccount customerAccount) {
		customerRepo.persist(customerAccount);
	}
}
