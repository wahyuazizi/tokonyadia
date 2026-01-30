package com.enigmacamp.tokonyadia.repository;

import com.enigmacamp.tokonyadia.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
