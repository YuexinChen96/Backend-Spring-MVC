package com.kevin.e_mall.exceptions;

public class pcOperationException extends RuntimeException{
	private static final long serialVersionUID = 4293704211474698307L;
	public pcOperationException(String msg) {
		//construction function
		super(msg);
	}
}
