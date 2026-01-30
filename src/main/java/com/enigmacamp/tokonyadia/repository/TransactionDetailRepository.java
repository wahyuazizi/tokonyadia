package com.enigmacamp.tokonyadia.repository;

import com.enigmacamp.tokonyadia.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, UUID> {
}
