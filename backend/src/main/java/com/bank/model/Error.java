package com.bank.model;

public class Error {
	private String errorMessage;
	
	
	public Error() {
		super();
	}

	public Error(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}