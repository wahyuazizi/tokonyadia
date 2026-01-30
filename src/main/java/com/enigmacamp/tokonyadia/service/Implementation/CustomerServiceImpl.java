package com.enigmacamp.tokonyadia.service.Implementation;

import com.enigmacamp.tokonyadia.entity.Customer;
import com.enigmacamp.tokonyadia.repository.CustomerRepository;
import com.enigmacamp.tokonyadia.service.CustomerService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> saveAllCustomers(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }


    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
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
    public Customer updateCustomer(Customer customer) {
        getCustomerById(customer.getId());
        return saveCustomer(customer);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.getCustomerByEmail(email);
    }

    @Override
    public Customer getCustomerFullname(String fullname) {
        return customerRepository.getCustomerFullname(fullname);
    }

    @Override
    public Customer getCustomerEmailandName(String email, String fullname) {
        return customerRepository.findByEmailAndFullname(email, fullname);
    }


}
