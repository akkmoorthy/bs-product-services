package com.bs.util;

import com.bs.entity.CustomerAccount;
import com.bs.entity.User;

public class SolrUtil {

	public static User indexUserData(CustomerAccount customer) {
		User user = new User();
		user.setId(String.valueOf(customer.getId()));
		user.setContactName(customer.getContactName());
		user.setUserName(customer.getUsername());
		user.setEmail(customer.getEmail());
		return user;
	}
}