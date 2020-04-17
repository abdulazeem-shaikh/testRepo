package com.internetbanking.app.customer.serviceImpl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.internetbanking.app.customer.model.Customer;
import com.internetbanking.app.customer.repository.CustomerRepositry;
import com.internetbanking.app.customer.service.CustomerService;
import com.internetbanking.app.customer.transaction.model.TransactionDetail;

/**
 * @author azeem
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Inject
	CustomerRepositry customerRepository;

	@Override
	public Customer createCustmer(Customer customer) {
		return customerRepository.createCustomer(customer);
	}

	/**
	 * delete customer
	 */
	@Override
	public boolean deleteCustomer(String customerId) {
		return customerRepository.deleteCustomer(customerId);
	}

	/**
	 * update the customer
	 */
	@Override
	public Customer updateCustomer(Customer customer, String customerId) {
		return customerRepository.updateCustomer(customer, customerId);
	}

	/**
	 *
	 */
	@Override
	public Customer getCustomerDetail(String customerId) {
		return customerRepository.getCustomer(customerId);
	}

	/**
	 * get customer
	 */
	@Override
	public TransactionDetail getCustomerTransactionHistory(String customerId) {
		return customerRepository.getTransactionHistory(customerId);

	}

	/**
	 * 
	 */
	@Override
	public TransactionDetail createCustomerTransactionHistory(TransactionDetail transactionDetail) {
		return customerRepository.createTransactionHistory(transactionDetail);
	}
}
