package com.te.booking.exception;

public class ExceptionHandler extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public ExceptionHandler(String message) {
		this.message=message;
		
	}

	public String getMessage() {
		return message;
	}
}
