package com.enigmacamp.tokonyadia.controller;

import com.enigmacamp.tokonyadia.entity.Customer;
import com.enigmacamp.tokonyadia.service.CustomerService;
import com.enigmacamp.tokonyadia.utils.constant.ApiUrlConstant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiUrlConstant.CUSTOMER)
public class CustomerController {
    CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PostMapping("/batch")
    public List<Customer> saveCustomers(@RequestBody List<Customer> customers) {
        return customerService.saveAllCustomers(customers);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable UUID id) {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable UUID id) {
        getCustomerById(id);
        customerService.deleteCustomer(id);
    }

    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer) {
        getCustomerById(customer.getId());
        return customerService.updateCustomer(customer);
    }

    @GetMapping("/emails/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }

    @GetMapping("/fullnames/{fullname}")
    public Customer getCustomerByFullname(@PathVariable String fullname) {
        return customerService.getCustomerFullname(fullname);
    }

    @GetMapping("/search")
    public Customer getCustomerByEmailAndFullname(@RequestParam  String email, @RequestParam String fullname) {
        return customerService.getCustomerEmailandName(email,fullname);
    }
}
