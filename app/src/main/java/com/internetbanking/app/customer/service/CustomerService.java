package com.internetbanking.app.customer.service;

import com.internetbanking.app.customer.model.Customer;
import com.internetbanking.app.customer.transaction.model.TransactionDetail;

public interface CustomerService {

	public Customer createCustmer(final Customer customer);

	public boolean deleteCustomer(final String id);

	public Customer updateCustomer(final Customer customer, String custmerId);

	public Customer getCustomerDetail(final String customerId);

	public TransactionDetail getCustomerTransactionHistory(final String customerId);

	public TransactionDetail createCustomerTransactionHistory(final TransactionDetail transactionDetail);

}
