package com.lmz.exception;

public class ChecksException extends Exception{
	private static final long serialVersionUID = 1L;

	public ChecksException() {
		super();
	}

	public ChecksException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ChecksException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChecksException(String message) {
		super(message);
	}

	public ChecksException(Throwable cause) {
		super(cause);
	}

	
}
