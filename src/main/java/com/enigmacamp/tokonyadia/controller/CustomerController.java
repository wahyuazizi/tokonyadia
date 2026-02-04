package com.enigmacamp.tokonyadia.controller;

import com.enigmacamp.tokonyadia.dto.request.CustomerRequest;
import com.enigmacamp.tokonyadia.dto.request.CustomerSearch;
import com.enigmacamp.tokonyadia.dto.request.ProductSearch;
import com.enigmacamp.tokonyadia.dto.response.CustomerResponse;
import com.enigmacamp.tokonyadia.dto.response.PageResponseWrapper;
import com.enigmacamp.tokonyadia.entity.Customer;
import com.enigmacamp.tokonyadia.service.CustomerService;
import com.enigmacamp.tokonyadia.utils.constant.ApiUrlConstant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiUrlConstant.CUSTOMER)
public class CustomerController {
    CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerResponse saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer).toResponse();
    }

//    @PostMapping("/batch")
//    public List<Customer> saveCustomers(@RequestBody List<Customer> customers) {
//        return customerService.saveAllCustomers(customers);
//    }

    @GetMapping
    public ResponseEntity<PageResponseWrapper<CustomerResponse>> getAllCustomers(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "3") Integer size,
            @RequestParam(name = "sort", defaultValue = "fullname") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection,
            @ModelAttribute CustomerSearch customerSearch) {
        int validatePage = page > 0 ? page - 1 : 0;
        Pageable pageable = PageRequest.of(validatePage, size, Sort.by(sortDirection, sortBy));
        return ResponseEntity.status(HttpStatus.OK).body(new PageResponseWrapper<>(customerService.getAllCustomers(pageable, customerSearch)));
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

    @PutMapping("/{id}")
    public Customer updateCustomer(@RequestBody CustomerRequest customer, @PathVariable UUID id) {
        return customerService.updateCustomer(customer, id);
    }
//
//    @GetMapping("/emails/{email}")
//    public Customer getCustomerByEmail(@PathVariable String email) {
//        return customerService.getCustomerByEmail(email);
//    }
//
//    @GetMapping("/fullnames/{fullname}")
//    public Customer getCustomerByFullname(@PathVariable String fullname) {
//        return customerService.getCustomerFullname(fullname);
//    }
//
//    @GetMapping("/search")
//    public Customer getCustomerByEmailAndFullname(@RequestParam  String email, @RequestParam String fullname) {
//        return customerService.getCustomerEmailandName(email,fullname);
//    }
}
