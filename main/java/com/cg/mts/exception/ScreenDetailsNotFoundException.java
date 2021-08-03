package com.cg.mts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ScreenDetailsNotFoundException extends RuntimeException {

	/**
	 * Exception class for Screen, if Screen details not found
	 */
	private static final long serialVersionUID = 1L;

	public ScreenDetailsNotFoundException(String message) {
		super(message);
	}
}
