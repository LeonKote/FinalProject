package com.leonkote.controller;

import com.leonkote.OperationHistoryApiApplicationTest;
import com.leonkote.controller.dto.CustomerDTO;
import com.leonkote.controller.dto.CustomersGetResponse;
import com.leonkote.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerControllerTest extends OperationHistoryApiApplicationTest
{
	@Autowired
	private CustomerController customerController;
	@Autowired
	private CustomerService customerService;

	@Test
	public void getCustomerTest()
	{
		customerService.init();

		assertEquals(2, customerController.getCustomers().getClients().size());

		CustomerDTO customer1 = customerController.getCustomer(0);
		CustomerDTO customer2 = customerController.getCustomer(1);

		assertEquals(0, customer1.getId());
		assertEquals("Spring", customer1.getName());
		assertEquals(1, customer2.getId());
		assertEquals("Boot", customer2.getName());

		assertEquals(2, customerController.getCustomers().getClients().size());
	}

	@Test
	public void getCustomersTest()
	{
		customerService.init();

		assertEquals(2, customerController.getCustomers().getClients().size());

		CustomersGetResponse customers = customerController.getCustomers();
		CustomerDTO customer1 = customers.getClients().get(0);
		CustomerDTO customer2 = customers.getClients().get(1);

		assertEquals(0, customer1.getId());
		assertEquals("Spring", customer1.getName());
		assertEquals(1, customer2.getId());
		assertEquals("Boot", customer2.getName());

		assertEquals(2, customerController.getCustomers().getClients().size());
	}

	@Test
	public void addCustomerTest()
	{
		customerService.init();

		assertEquals(2, customerController.getCustomers().getClients().size());

		customerController.addCustomer(new CustomerDTO(-1, "newuser"));

		assertEquals(3, customerController.getCustomers().getClients().size());

		customerController.addCustomer(new CustomerDTO(-1, "omgnewuser"));

		assertEquals(4, customerController.getCustomers().getClients().size());
	}

	@Test
	public void deleteCustomerTest()
	{
		customerService.init();

		assertEquals(2, customerController.getCustomers().getClients().size());

		customerController.deleteCustomer(0);

		assertEquals(1, customerController.getCustomers().getClients().size());

		customerController.deleteCustomer(1);

		assertEquals(0, customerController.getCustomers().getClients().size());
	}
}
