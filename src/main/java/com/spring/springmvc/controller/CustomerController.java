package com.spring.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springmvc.entity.CustomerAccount;
import com.spring.springmvc.repository.CustomerAccountRepository;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/demo") // This means URL's start with /demo (after
								// Application path)
public class CustomerController {
	@Autowired
	private CustomerAccountRepository customerAccountRepository;

	@GetMapping(path = "/add")
	public @ResponseBody String addNewUser(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String email, @RequestParam String password) {
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setFirstName(firstName);
		customerAccount.setLastName(lastName);
		customerAccount.setEmail(email);
		customerAccount.setPassword(password);

		customerAccountRepository.save(customerAccount);
		return "Saved";
	}

	// @RequestMapping(value = "/user", method = RequestMethod.GET, produces =
	// "application/json")
	@GetMapping(path = "/user")
	public @ResponseBody CustomerAccount getUser(@RequestParam Integer userId) {
		// This returns a JSON or XML with the users
		return customerAccountRepository.findOne(userId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	// @GetMapping(path = "/all")
	public @ResponseBody Iterable<CustomerAccount> getAllUsers() {
		// This returns a JSON or XML with the users
		return customerAccountRepository.findAll();
	}
}