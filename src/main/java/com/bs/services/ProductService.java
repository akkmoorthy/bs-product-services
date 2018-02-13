package com.bs.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.AddressDO;
import com.bs.CityDO;
import com.bs.TheatreDO;
import com.bs.entity.Movie;
import com.bs.entity.Theatre;
import com.bs.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<TheatreDO> retrieveAllTheatres(Integer cityId) {
		List<Theatre> list = productRepository.getAllTheatres(cityId);

		return convertToDO(list);
	}

	private List<TheatreDO> convertToDO(List<Theatre> list) {
		List<TheatreDO> theatreList = new ArrayList<TheatreDO>();
		for (Theatre theatre : list) {
			TheatreDO theatreDO = new TheatreDO();
			theatreDO.setId(theatre.getId());
			theatreDO.setTheatreName(theatre.getTheatreName());
			theatreDO.setCreatedDateTime(theatre.getCreatedDateTime());

			CityDO city = new CityDO();
			city.setId(theatre.getCity().getId());
			city.setCityName(theatre.getCity().getCityName());
			city.setState(theatre.getCity().getState());
			theatreDO.setCity(city);

			AddressDO address = new AddressDO();
			address.setId(theatre.getAddress().getId());
			address.setAddress1(theatre.getAddress().getAddress1());
			address.setAddress2(theatre.getAddress().getAddress2());
			address.setCity(theatre.getAddress().getCity());
			address.setState(theatre.getAddress().getState());
			address.setCountry(theatre.getAddress().getCountry());
			theatreDO.setAddress(address);

			theatreList.add(theatreDO);
		}

		return theatreList;
	}

	public List<Movie> retrieveAllMovies() {
		return productRepository.findAllMovies();
	}
}