package com.garagecontrolsystem.service.exceptions;

public class ConstraintViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConstraintViolationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ConstraintViolationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
