package com.bs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bs.TheatreDO;
import com.bs.entity.Movie;
import com.bs.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/theatres/{cityId}", method = RequestMethod.GET)
	public List<TheatreDO> getTheatreList(@PathVariable Integer cityId) {
		return productService.retrieveAllTheatres(cityId);
	}

	@RequestMapping(value = "/movies", method = RequestMethod.GET)
	public List<Movie> getMoviesList() {
		return productService.retrieveAllMovies();
	}
}