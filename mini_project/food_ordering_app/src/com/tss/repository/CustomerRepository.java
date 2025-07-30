package com.tss.repository;

import java.util.ArrayList;
import java.util.List;

import com.tss.model.Customer;
import com.tss.util.SerializationUtil;

public class CustomerRepository {
	private static final String CUSTOMER_FILE = "customers.ser";
	private List<Customer> customers;

	public CustomerRepository() {
		this.customers = SerializationUtil.readList(CUSTOMER_FILE);
		if (customers == null) {
			customers = new ArrayList<>();
		}
	}

	public List<Customer> getAll() {
		return customers;
	}

	public void add(Customer customer) {
		customers.add(customer);
		save();
	}

	public Customer findByCredentials(String username, String password) {
		return customers.stream().filter(c -> c.authenticate(username, password)).findFirst().orElse(null);
	}

	public boolean existsByUsername(String username) {
		return customers.stream().anyMatch(c -> c.getUsername().equals(username));
	}

	public void save() {
		SerializationUtil.saveList(customers, CUSTOMER_FILE);
	}
}