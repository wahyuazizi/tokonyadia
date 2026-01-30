package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.dto.request.CustomerRequest;
import com.enigmacamp.tokonyadia.dto.response.CustomerResponse;
import com.enigmacamp.tokonyadia.entity.Customer;
import com.enigmacamp.tokonyadia.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Customer saveCustomer(CustomerRequest customer);
    List<Customer> saveAllCustomers(List<Customer> customers);
    List<CustomerResponse> getAllCustomers();
    Customer getCustomerById(UUID id);
    void deleteCustomer(UUID id);
    Customer updateCustomer(CustomerRequest customer, UUID id);

    Customer getCustomerByEmail(String email);
    Customer getCustomerFullname(String fullname);
    Customer getCustomerEmailandName(String email, String fullname);
}
