package com.yrgo.services.customers;

public class CustomerNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException() {
		super("Customer not found");
	}

	public CustomerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
