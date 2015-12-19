package com.ds.appmanager.batch.exceptions;

public class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BaseException() {
	}

	public BaseException(String msg) {
		super(msg);
	}

	public BaseException(int errCode) {
		
	}

	public BaseException(int errCode , String errMsg) {

	}

}
