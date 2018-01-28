package com.bs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bs.entity.User;
import com.bs.services.IndexService;

@RestController
@RequestMapping("/search")
public class UserSearchController {

	@Autowired
	private IndexService indexService;

	@RequestMapping(value = "/{term}", method = RequestMethod.GET)
	public List<User> getGreeting(@PathVariable String term) {
		return indexService.search(term);
	}
}