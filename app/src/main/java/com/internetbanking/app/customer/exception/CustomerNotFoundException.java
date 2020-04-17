package com.internetbanking.app.customer.exception;
public class CustomerNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException() {
        super("Customer not found");
    }

}