package com.enigmacamp.tokonyadia.repository;

import com.enigmacamp.tokonyadia.config.JpaAuditingConfig;
import com.enigmacamp.tokonyadia.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.Instant;
import java.util.List;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;



@DataJpaTest
@Import(JpaAuditingConfig.class)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer savedCustomer1;
    private Customer savedCustomer2;

    @BeforeEach
    void setUp() {
        savedCustomer1 = Customer.builder()
                .fullname("Wahyu Azizi")
                .email("wahyu@mail.com")
                .build();

        savedCustomer2 = Customer.builder()
                .fullname("Cattleya")
                .email("leya@mail.com")
                .build();
    }

    @Test
    void findCustomerByFullnameIsLikeIgnoreCaseOrEmailIsLike() {

        customerRepository.saveAll(List.of(savedCustomer1,savedCustomer2));
        List<Customer> result = customerRepository.findCustomerByFullnameIsLikeIgnoreCaseOrEmailIsLike("%wahyu%", "%wahyu@mail.com");

        assertThat(result).hasSize(1);

    }
}