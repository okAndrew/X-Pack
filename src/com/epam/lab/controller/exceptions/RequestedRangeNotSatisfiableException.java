package com.epam.lab.controller.exceptions;

public class RequestedRangeNotSatisfiableException extends Exception{

	private static final long serialVersionUID = 1L;

	public RequestedRangeNotSatisfiableException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestedRangeNotSatisfiableException(String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public RequestedRangeNotSatisfiableException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RequestedRangeNotSatisfiableException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RequestedRangeNotSatisfiableException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}