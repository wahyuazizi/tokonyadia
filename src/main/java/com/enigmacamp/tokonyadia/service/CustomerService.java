package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.dto.request.CustomerRequest;
import com.enigmacamp.tokonyadia.dto.request.CustomerSearch;
import com.enigmacamp.tokonyadia.dto.response.CustomerResponse;
import com.enigmacamp.tokonyadia.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
//    List<Customer> saveAllCustomers(List<Customer> customers);
    Page<CustomerResponse> getAllCustomers(Pageable pageable, CustomerSearch customerSearch);
    Customer getCustomerById(UUID id);
    void deleteCustomer(UUID id);
    Customer updateCustomer(CustomerRequest customer, UUID id);

//    Customer getCustomerByEmail(String email);
//    Customer getCustomerFullname(String fullname);
//    Customer getCustomerEmailandName(String email, String fullname);
}
