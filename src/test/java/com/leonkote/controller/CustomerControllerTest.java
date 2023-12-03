package com.leonkote.controller;

import com.leonkote.OperationHistoryApiApplicationTest;
import com.leonkote.controller.dto.CustomerDTO;
import com.leonkote.controller.dto.CustomersGetResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerControllerTest extends OperationHistoryApiApplicationTest
{
	@Autowired
	private CustomerController customerController;

	@Test
	public void getCustomerTest()
	{
		int count = customerController.getCustomers().getClients().size();

		assertEquals(count, customerController.getCustomers().getClients().size());

		CustomerDTO customer1 = customerController.getCustomer(0);
		CustomerDTO customer2 = customerController.getCustomer(1);

		assertEquals(0, customer1.getId());
		assertEquals("Spring", customer1.getName());
		assertEquals(1, customer2.getId());
		assertEquals("Boot", customer2.getName());

		assertEquals(count, customerController.getCustomers().getClients().size());
	}

	@Test
	public void getCustomersTest()
	{
		int count = customerController.getCustomers().getClients().size();

		assertEquals(count, customerController.getCustomers().getClients().size());

		CustomersGetResponse customers = customerController.getCustomers();
		CustomerDTO customer1 = customers.getClients().get(0);
		CustomerDTO customer2 = customers.getClients().get(1);

		assertEquals(0, customer1.getId());
		assertEquals("Spring", customer1.getName());
		assertEquals(1, customer2.getId());
		assertEquals("Boot", customer2.getName());

		assertEquals(count, customerController.getCustomers().getClients().size());
	}

	@Test
	public void addCustomerTest()
	{
		int count = customerController.getCustomers().getClients().size();

		assertEquals(count, customerController.getCustomers().getClients().size());

		customerController.addCustomer(new CustomerDTO(-1, "newuser"));

		assertEquals(count + 1, customerController.getCustomers().getClients().size());

		customerController.addCustomer(new CustomerDTO(-1, "omgnewuser"));

		assertEquals(count + 2, customerController.getCustomers().getClients().size());
	}

	@Test
	public void deleteCustomerTest()
	{
		int count = customerController.getCustomers().getClients().size();

		assertEquals(count, customerController.getCustomers().getClients().size());

		customerController.deleteCustomer(0);

		assertEquals(count - 1, customerController.getCustomers().getClients().size());

		customerController.deleteCustomer(1);

		assertEquals(count - 2, customerController.getCustomers().getClients().size());
	}
}
