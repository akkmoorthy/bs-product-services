package com.bs;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TheatreDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2733433646145145405L;
	private int id;
	private String theatreName;
	private LocalDateTime createdDateTime;
	private CityDO city;
	private AddressDO address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public CityDO getCity() {
		return city;
	}

	public void setCity(CityDO city) {
		this.city = city;
	}

	public AddressDO getAddress() {
		return address;
	}

	public void setAddress(AddressDO address) {
		this.address = address;
	}
}