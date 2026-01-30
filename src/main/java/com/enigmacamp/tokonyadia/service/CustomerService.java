package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.entity.Customer;
import com.enigmacamp.tokonyadia.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> saveAllCustomers(List<Customer> customers);
    List<Customer> getAllCustomers();
    Customer getCustomerById(UUID id);
    void deleteCustomer(UUID id);
    Customer updateCustomer(Customer customer);

    Customer getCustomerByEmail(String email);
    Customer getCustomerFullname(String fullname);
    Customer getCustomerEmailandName(String email, String fullname);
}
