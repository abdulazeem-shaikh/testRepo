package com.internetbanking.app.customer.exception;

public class TransactionHistoryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionHistoryNotFoundException() {
		super("Transaction not found exception");
	}

}