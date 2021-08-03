package com.cg.mts.exception;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class SeatNotFoundException extends RuntimeException{
	/**
	 * Exception class for seat, if not found
	 */
	private static final long serialVersionUID = 1L;

	public SeatNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}

