package com.enigmacamp.tokonyadia.repository;

import com.enigmacamp.tokonyadia.entity.Customer;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID>, JpaSpecificationExecutor<Customer> {

    List<Customer> findCustomerByFullnameIsLikeIgnoreCaseOrEmailIsLike(String fullname, String email);
}
