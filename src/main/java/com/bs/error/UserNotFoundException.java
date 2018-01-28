package com.bs.error;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;

	public UserNotFoundException(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}
}