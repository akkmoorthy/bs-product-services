package com.bs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.entity.CustomerAccount;
import com.bs.entity.User;
import com.bs.repository.CustomerAccountRepository;
import com.bs.repository.SolrUserRepository;
import com.bs.util.SolrUtil;

@Service
public class IndexService {

	@Autowired
	private CustomerAccountRepository custRepo;

	@Autowired
	private SolrUserRepository users;

	public void indexUserData() {
		List<CustomerAccount> custs = custRepo.findAll();

		for (CustomerAccount customer : custs) {

			User user = SolrUtil.indexUserData(customer);
			users.save(user);
		}

	}

	public List<User> search(String searchTerm) {
		return users.findByContactName(searchTerm);
	}
}