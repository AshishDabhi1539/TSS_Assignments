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

    /**
     * Gets all customers.
     * @return List of customers.
     */
    public List<Customer> getAll() {
        return customers;
    }

    /**
     * Adds a customer to the repository.
     * @param customer The customer to add.
     */
    public void add(Customer customer) {
        customers.add(customer);
        save();
    }

    /**
     * Finds a customer by username and password.
     * @param username The username.
     * @param password The password.
     * @return The customer if found, null otherwise.
     */
    public Customer findByCredentials(String username, String password) {
        return customers.stream()
                .filter(c -> c.authenticate(username, password))
                .findFirst()
                .orElse(null);
    }

    /**
     * Checks if a username is already taken.
     * @param username The username to check.
     * @return True if the username exists, false otherwise.
     */
    public boolean existsByUsername(String username) {
        return customers.stream().anyMatch(c -> c.getUsername().equals(username));
    }

    /**
     * Saves the customers to persistent storage.
     */
    public void save() {
        SerializationUtil.saveList(customers, CUSTOMER_FILE);
    }
}