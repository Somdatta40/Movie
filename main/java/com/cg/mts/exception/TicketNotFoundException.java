package com.cg.mts.exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class TicketNotFoundException extends RuntimeException{
	/**
	 * Exception class for Ticket, if Ticket details not found
	 */
	private static final long serialVersionUID = 1L;

	public TicketNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}