package com.leonkote.service;

import com.leonkote.OperationHistoryApiApplicationTest;
import com.leonkote.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerServiceTest extends OperationHistoryApiApplicationTest
{
	@Autowired
	private CustomerService customerService;

	@Test
	public void getCustomerTest()
	{
		int count = customerService.getCustomers().size();

		assertEquals(count, customerService.getCustomers().size());

		Customer customer1 = customerService.getCustomer(0);
		Customer customer2 = customerService.getCustomer(1);

		assertEquals(0, customer1.getId());
		assertEquals("Spring", customer1.getName());
		assertEquals(1, customer2.getId());
		assertEquals("Boot", customer2.getName());

		assertEquals(count, customerService.getCustomers().size());
	}

	@Test
	public void getCustomersTest()
	{
		int count = customerService.getCustomers().size();

		assertEquals(count, customerService.getCustomers().size());

		List<Customer> customers = customerService.getCustomers();
		Customer customer1 = customers.get(0);
		Customer customer2 = customers.get(1);

		assertEquals(0, customer1.getId());
		assertEquals("Spring", customer1.getName());
		assertEquals(1, customer2.getId());
		assertEquals("Boot", customer2.getName());

		assertEquals(count, customerService.getCustomers().size());
	}

	@Test
	public void addCustomerTest()
	{
		int count = customerService.getCustomers().size();

		assertEquals(count, customerService.getCustomers().size());

		customerService.addCustomer("newuser");

		assertEquals(count + 1, customerService.getCustomers().size());

		customerService.addCustomer("omgnewuser");

		assertEquals(count + 2, customerService.getCustomers().size());
	}
}
