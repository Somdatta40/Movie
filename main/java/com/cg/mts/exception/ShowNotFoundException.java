package com.cg.mts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ShowNotFoundException extends RuntimeException {

	/**
	 * Exception class for Show, if Show details not found
	 */
	private static final long serialVersionUID = 1L;

	public ShowNotFoundException(String message) {
		super(message);
	}

}
