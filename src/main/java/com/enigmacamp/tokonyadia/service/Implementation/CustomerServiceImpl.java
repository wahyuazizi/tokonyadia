package com.enigmacamp.tokonyadia.service.Implementation;

import com.enigmacamp.tokonyadia.dto.request.CustomerRequest;
import com.enigmacamp.tokonyadia.dto.request.CustomerSearch;
import com.enigmacamp.tokonyadia.dto.response.CustomerResponse;
import com.enigmacamp.tokonyadia.entity.Customer;
import com.enigmacamp.tokonyadia.repository.CustomerRepository;
import com.enigmacamp.tokonyadia.service.CustomerService;
import com.enigmacamp.tokonyadia.spesification.CustomerSpecification;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customerPayload) {
//        Customer customer = Customer.builder()
//                .fullname(customerPayload.fullname())
//                .email(customerPayload.email())
//                .address(customerPayload.address())
//                .gender(customerPayload.gender())
//                .member(customerPayload.memberId())
//                .build();
        Customer customer = customerRepository.save(customerPayload);
        return customerRepository.save(customer);
    }

//    @Override
//    public List<Customer> saveAllCustomers(List<Customer> customers) {
//        return customerRepository.saveAll(customers);
//    }


    @Override
    public Page<CustomerResponse> getAllCustomers(Pageable pageable, CustomerSearch customerSearch) {
        Specification<Customer> customerSpecification = CustomerSpecification.getSpecification(customerSearch);
        return customerRepository.findAll(customerSpecification, pageable)
                .map(Customer::toResponse);
    }

    @Override
    public Customer getCustomerById(UUID id) {
        try {
            if(!customerRepository.existsById(id)){
                throw new ChangeSetPersister.NotFoundException();
            }
            return customerRepository.getReferenceById(id);
        }catch (ChangeSetPersister.NotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteCustomer(UUID id) {
        getCustomerById(id);
        customerRepository.deleteById(id);
    }

    @Override
    public Customer updateCustomer(CustomerRequest customerPayload, UUID id) {
        getCustomerById(id);
        Customer customer = Customer.builder()
                .id(id)
                .fullname(customerPayload.fullname())
                .email(customerPayload.email())
                .address(customerPayload.address())
                .gender(customerPayload.gender())
                .member(customerPayload.memberId())
                .build();
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomerByFullNameOrEmail(String fullname, String email) {
        return customerRepository.findCustomerByFullnameIsLikeIgnoreCaseOrEmailIsLike(fullname, email);
    }

    //    @Override
//    public Customer getCustomerByEmail(String email) {
//        return customerRepository.getCustomerByEmail(email);
//    }
//
//    @Override
//    public Customer getCustomerFullname(String fullname) {
//        return customerRepository.getCustomerFullname(fullname);
//    }
//
//    @Override
//    public Customer getCustomerEmailandName(String email, String fullname) {
//        return customerRepository.findByEmailAndFullname(email, fullname);
//    }


}
