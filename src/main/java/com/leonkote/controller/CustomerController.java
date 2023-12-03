package com.leonkote.controller;

import com.leonkote.controller.dto.CustomerDTO;
import com.leonkote.controller.dto.CustomersGetResponse;
import com.leonkote.domain.Customer;
import com.leonkote.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
public class CustomerController
{
	private final CustomerService customerService;

	@GetMapping
	public CustomersGetResponse getCustomers()
	{
		List<Customer> customers = customerService.getCustomers();
		List<CustomerDTO> customerDTOS = customers.stream()
				.map(customer -> new CustomerDTO(customer.getId(), customer.getName()))
				.collect(Collectors.toList());
		return new CustomersGetResponse(customerDTOS);
	}

	@GetMapping("{id}")
	public CustomerDTO getCustomer(@PathVariable(name = "id") int id)
	{
		Customer customer = customerService.getCustomer(id);
		return new CustomerDTO(customer.getId(), customer.getName());
	}

	@DeleteMapping("{id}")
	public void deleteCustomer(@PathVariable(name = "id") int id)
	{
		customerService.deleteCustomer(id);
	}

	@PostMapping
	public void addCustomer(@RequestBody CustomerDTO customerDTO)
	{
		customerService.addCustomer(customerDTO.getName());
	}
}
