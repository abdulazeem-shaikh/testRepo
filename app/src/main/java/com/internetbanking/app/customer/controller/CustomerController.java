/**
 * 
 */
package com.internetbanking.app.customer.controller;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.PathParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internetbanking.app.customer.constant.InternateBankingAppConstant;
import com.internetbanking.app.customer.exception.CustomerExistException;
import com.internetbanking.app.customer.exception.CustomerNotFoundException;
import com.internetbanking.app.customer.model.Customer;
import com.internetbanking.app.customer.service.CustomerService;
import com.mongodb.lang.NonNull;

/**
 * @author azeem
 *
 */

@RestController
@RequestMapping(InternateBankingAppConstant.ROOT_APP_PATH)
public class CustomerController {

	@Inject
	CustomerService customerService;

	/**
	 * get customer detail
	 * 
	 * @param customerId
	 * @return
	 */
	@GetMapping(InternateBankingAppConstant.CUSTOMER)
	public Customer getCustomer(@NonNull @RequestParam("customerId") final String customerId) {
		Optional<Customer> customer = Optional.of(customerService.getCustomerDetail(customerId));
		if (customer.isPresent()) {
			return customer.get();
		}
		throw new CustomerNotFoundException();
	}

	/**
	 * create the customer
	 * 
	 * @param customer
	 * @return
	 */
	@PostMapping(InternateBankingAppConstant.CUSTOMER)
	public Customer createCustomer(@RequestBody Customer customer) {
		Optional<Customer> customerReponse = Optional.ofNullable(customerService.createCustmer(customer));
		if (customerReponse.isPresent()) {
			return customerReponse.get();
		}
		throw new CustomerExistException();
	}

	/**
	 * update customer
	 * 
	 * @param customer
	 * @param customerId
	 * @return
	 */
	@PutMapping(InternateBankingAppConstant.CUSTOMER)
	public Customer updateCustomer(@NonNull @RequestBody Customer customer,
			@NonNull @RequestParam("customerId") String customerId) {
		Optional<Customer> response = Optional.of(customerService.updateCustomer(customer, customerId));
		if (response.isPresent()) {
			return response.get();
		}
		throw new CustomerNotFoundException();
	}

	/**
	 * delete customer
	 * 
	 * @param customerId
	 * @return
	 */
	@DeleteMapping(InternateBankingAppConstant.CUSTOMER)
	public boolean deleteCustomer(@NonNull @PathParam("customerId") String customerId) {
		boolean isDeleted = customerService.deleteCustomer(customerId);

		if (isDeleted) {
			return isDeleted;
		}
		throw new CustomerNotFoundException();
	}

}
