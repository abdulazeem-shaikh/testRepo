package com.internetbanking.banking.app;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.internetbanking.app.customer.controller.CustomerController;
import com.internetbanking.app.customer.model.Customer;
import com.internetbanking.app.customer.service.CustomerService;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CustomerService customerService;

	@Test
	public void getCustomerTest() throws Exception {
		given(this.customerService.getCustomerDetail("1234512")).willReturn(prepareCustomerDetail());
		this.mvc.perform(get("/api/v1/service/customer?customerId=1234512").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(new JsonMapper().writeValueAsString(prepareCustomerDetail())));
	}

	private Customer prepareCustomerDetail() {
		Customer customer = new Customer();
		customer.setAddress("HYD");
		customer.setCustomerId("12345");
		customer.setName("abdul");
		return customer;
	}

}