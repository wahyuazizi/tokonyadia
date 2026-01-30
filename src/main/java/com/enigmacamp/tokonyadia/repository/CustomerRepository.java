package com.enigmacamp.tokonyadia.repository;

import com.enigmacamp.tokonyadia.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query("SELECT c FROM  Customer c WHERE c.email=:email")
    Customer getCustomerByEmail(@Param("email") String email);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.fullname)=:fullname")
    Customer getCustomerFullname(@Param("fullname") String fullname);

    Customer findByEmailAndFullname(String emailAddress, String lastname);
}
