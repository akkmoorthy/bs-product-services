package com.bs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bs.entity.CustomerAccount;
import com.bs.error.Error;
import com.bs.error.UserNotFoundException;
import com.bs.services.UserService;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/user") // This means URL's start with /demo (after
								// Application path)
public class CustomerController {
	@Autowired
	private UserService userService;

	@GetMapping("/add")
	// http://localhost:9081/user/add?name=Manu&userName=manu&email=manu@gmail.com&password=password
	public @ResponseBody String addNewUser(@RequestParam(value = "name", defaultValue = "anonymous") String contactName,
			@RequestParam String userName, @RequestParam String email, @RequestParam String password) {
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUsername(userName);
		customerAccount.setContactName(contactName);
		customerAccount.setEmail(email);
		customerAccount.setPassword(password);

		Integer id = userService.saveUser(customerAccount);
		return "Saved" + id;
	}

	@PostMapping(value = "/add", consumes = "application/json")
	// { "contactName": "Shambu", "username": "shambu", "email": "shambu@gmail.com",
	// "password": "password"}
	public @ResponseBody String addUser(@RequestBody CustomerAccount customerAccount) {
		Integer id = userService.saveUser(customerAccount);
		return "Saved " + id;
	}

	// params is optional
	@RequestMapping(value = "/adduser/{userName}/{email}", params = { "contactName", "password" })
	// http://localhost:9081/user/adduser/shaam/shaam@gmail.com?contactName=Shaam&password=password
	public @ResponseBody String addNewUserWithPathVariable(@PathVariable String userName,
			@RequestParam String contactName, @PathVariable String email, @RequestParam String password) {
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUsername(userName);
		customerAccount.setContactName(contactName);
		customerAccount.setEmail(email);
		customerAccount.setPassword(password);

		userService.persistUser(customerAccount);
		return "Saved";
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getUser(@PathVariable Integer userId) {
		CustomerAccount cust = userService.findOneUser(userId);
		if (cust == null) {
			Error error = new Error(404, "User Id [" + userId + "] is not found  while using JPA");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CustomerAccount>(cust, HttpStatus.OK);
	}

	// When there is an UserNotFoundException thrown then this method gets called
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Error> spittleNotFound(UserNotFoundException e) {
		long userId = e.getUserId();
		Error error = new Error(404, "User Id [" + userId + "] is not found");
		return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
	public CustomerAccount findOne(@PathVariable Integer userId) {
		CustomerAccount cust = userService.findUser(userId);
		if (cust == null) {
			throw new UserNotFoundException(userId);
		}
		return cust;
	}

	@RequestMapping(value = { "/all", "/users" }, method = RequestMethod.GET, produces = "application/json")
	// @GetMapping(path = "/all")
	public Iterable<CustomerAccount> getAllUsers() {
		// This returns a JSON or XML with the users
		return userService.getAllUsers();
	}
}