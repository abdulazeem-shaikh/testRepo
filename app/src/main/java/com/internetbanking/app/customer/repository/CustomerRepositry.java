package com.internetbanking.app.customer.repository;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.internetbanking.app.customer.model.Customer;
import com.internetbanking.app.customer.transaction.model.TransactionDetail;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;

@Repository
public class CustomerRepositry {

	private static final Logger LOGGER = Logger.getLogger(CustomerRepositry.class);
	MongoCollection<Document> customerCollection;
	MongoCollection<Document> transactionCollection;

	@Inject
	public CustomerRepositry(@Named("customer") MongoCollection<Document> customerCollection,
			@Named("transaction") MongoCollection<Document> transactionCollection) {
		this.customerCollection = customerCollection;
		this.transactionCollection = transactionCollection;
	}

	/**
	 * reterive the customer from db
	 * 
	 * @param customerId
	 * @return
	 */
	public Customer getCustomer(final String customerId) {
		final Document customerFilter = new Document();
		customerFilter.append("customerId", customerId);
		Optional<Customer> customer = Optional.of(customerCollection.find(customerFilter, Customer.class).first());
		if (customer.isPresent()) {
			return customer.get();
		}
		LOGGER.error("customer is detail is not available for the corresponding id " + customerId);
		return new Customer();
	}

	/**
	 * update the customer in db
	 * 
	 * @param customer
	 * @param customerId
	 * @return
	 */
	public Customer updateCustomer(final Customer customer, final String customerId) {
		final Document customerFilter = new Document();
		customerFilter.append("customerId", customerId);
		final Optional<Customer> existingCustomer = Optional
				.of(customerCollection.find(customerFilter, Customer.class).first());
		if (existingCustomer.isPresent()) {

			ObjectMapper objectMapper = new ObjectMapper();
			String customerString;
			try {
				customerString = objectMapper.writeValueAsString(customer);
				Document doc = Document.parse(customerString);
				doc.put("lastVisitedAt", customer.getLastVisitedAt());
				customerCollection.replaceOne(customerFilter, doc);
				LOGGER.info("Successfully created the customer with " + customer.getCustomerId());
				return customer;
			} catch (JsonProcessingException e) {
				LOGGER.error("unable to create customer", e);
				return null;
			}
		}
		return null;
	}

	/**
	 * delete the customer by id
	 * 
	 * @param customerId
	 * @return
	 */
	public boolean deleteCustomer(String customerId) {
		final Document customerFilter = new Document();
		customerFilter.append("customerId", customerId);
		Optional<DeleteResult> deleteResult = Optional.of(customerCollection.deleteMany(customerFilter));

		if (deleteResult.isPresent()) {
			if (deleteResult.get().getDeletedCount() > 0) {
				LOGGER.info("Customer is deleted with the id " + customerId);
				return true;
			}
		}
		LOGGER.error("Failed to delete the customer " + customerId);
		return false;
	}

	public Customer createCustomer(Customer customer) {

		final Document customerFilter = new Document();
		customerFilter.append("customerId", customer.getCustomerId());
		final Optional<Customer> existingCustomer = Optional
				.ofNullable(customerCollection.find(customerFilter, Customer.class).first());
		if (existingCustomer.isPresent()) {
			LOGGER.error("customer exit..");
			return null;
		}

		ObjectMapper objectMapper = new ObjectMapper();
		String customerString;
		try {
			customerString = objectMapper.writeValueAsString(customer);
			Document doc = Document.parse(customerString);
			doc.put("lastVisitedAt", customer.getLastVisitedAt());
			customerCollection.insertOne(doc);
			LOGGER.info("Successfully created the customer with " + customer.getCustomerId());
			return customer;
		} catch (JsonProcessingException e) {
			LOGGER.error("unable to create customer", e);
			return null;
		}
	}

	public TransactionDetail getTransactionHistory(String customerId) {
		Document filter = new Document();
		filter.append("customerId", customerId);
		final Optional<TransactionDetail> transactionDetail = Optional
				.of(transactionCollection.find(filter, TransactionDetail.class).first());
		if (transactionDetail.isPresent()) {
			return transactionDetail.get();
		}
		LOGGER.info("No transaction detail is availbel" + customerId);
		return new TransactionDetail();

	}

	public TransactionDetail createTransactionHistory(TransactionDetail transactionDetail) {
		ObjectMapper objectMapper = new ObjectMapper();
		String customerString;
		try {
			customerString = objectMapper.writeValueAsString(transactionDetail);
			Document doc = Document.parse(customerString);
			doc.put("lastVisitedAt", transactionDetail.getLastVisited());
			transactionCollection.insertOne(doc);
			LOGGER.info("Successfully created the customer with " + transactionDetail.getCustomerId());
			return transactionDetail;
		} catch (JsonProcessingException e) {
			LOGGER.error("unable to create customer", e);
			return null;
		}
	}

}
