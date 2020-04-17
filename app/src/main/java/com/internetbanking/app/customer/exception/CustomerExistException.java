package com.internetbanking.app.customer.exception;
public class CustomerExistException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerExistException() {
        super("Customer exit exception");
    }

}