package com.leonkote.service;

import com.leonkote.domain.Customer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomerService
{
	private final Map<Integer, Customer> storage = new HashMap<>();
	private int nextId;

	public void addCustomer(String name)
	{
		Customer customer = new Customer(nextId, name);
		storage.put(nextId, customer);
		nextId++;
	}

	public List<Customer> getCustomers()
	{
		return new ArrayList<>(storage.values());
	}

	public Customer getCustomer(int id)
	{
		return storage.get(id);
	}

	public void deleteCustomer(int id)
	{
		storage.remove(id);
	}

	@PostConstruct
	public void init()
	{
		addCustomer("Spring");
		addCustomer("Boot");
	}
}
