package com.kevin.e_mall.exceptions;

public class ShopOperationException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShopOperationException(String msg) {
		//construction function
		super(msg);
	}
}
