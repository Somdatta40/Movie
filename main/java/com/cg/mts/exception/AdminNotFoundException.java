package com.cg.mts.exception;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class AdminNotFoundException extends RuntimeException{
	/**
	 * Exception class for admin, if not found
	 */
	private static final long serialVersionUID = 1L;

	public AdminNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
