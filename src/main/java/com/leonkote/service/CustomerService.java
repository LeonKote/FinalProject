package com.leonkote.service;

import com.leonkote.domain.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomerService
{
	private final List<Customer> storage = new ArrayList<>();

	public void addCustomer(String name)
	{
		int id = storage.size();
		Customer customer = new Customer(id, name);
		storage.add(customer);
	}

	public List<Customer> getCustomers()
	{
		return storage;
	}

	public Customer getCustomer(int id)
	{
		return storage.get(id);
	}

	@PostConstruct
	public void init()
	{
		addCustomer("Spring");
		addCustomer("Boot");
	}
}
