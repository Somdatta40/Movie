package com.cg.mts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookingNotFoundException extends RuntimeException {

	/**
	 * Exception class for booking, if booking not found
	 */
	private static final long serialVersionUID = 1L;

	public BookingNotFoundException(String message) {
		super(message);
	}

}