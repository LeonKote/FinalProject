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
		customerService.init();

		assertEquals(2, customerService.getCustomers().size());

		Customer customer1 = customerService.getCustomer(0);
		Customer customer2 = customerService.getCustomer(1);

		assertEquals(0, customer1.getId());
		assertEquals("Spring", customer1.getName());
		assertEquals(1, customer2.getId());
		assertEquals("Boot", customer2.getName());

		assertEquals(2, customerService.getCustomers().size());
	}

	@Test
	public void getCustomersTest()
	{
		customerService.init();

		assertEquals(2, customerService.getCustomers().size());

		List<Customer> customers = customerService.getCustomers();
		Customer customer1 = customers.get(0);
		Customer customer2 = customers.get(1);

		assertEquals(0, customer1.getId());
		assertEquals("Spring", customer1.getName());
		assertEquals(1, customer2.getId());
		assertEquals("Boot", customer2.getName());

		assertEquals(2, customerService.getCustomers().size());
	}

	@Test
	public void addCustomerTest()
	{
		customerService.init();

		assertEquals(2, customerService.getCustomers().size());

		customerService.addCustomer("newuser");

		assertEquals(3, customerService.getCustomers().size());

		customerService.addCustomer("omgnewuser");

		assertEquals(4, customerService.getCustomers().size());
	}

	@Test
	public void deleteCustomerTest()
	{
		customerService.init();

		assertEquals(2, customerService.getCustomers().size());

		customerService.deleteCustomer(0);

		assertEquals(1, customerService.getCustomers().size());

		customerService.deleteCustomer(1);

		assertEquals(0, customerService.getCustomers().size());
	}
}
