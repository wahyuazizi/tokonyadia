package com.enigmacamp.tokonyadia.repository;

import com.enigmacamp.tokonyadia.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID>, JpaSpecificationExecutor<Transaction> {

    @Query("""
    SELECT COALESCE(SUM(td.priceSell * td.quantity), 0)
    FROM TransactionDetail td
    WHERE td.transaction.id = :transactionId
""")
    Double getTotalByTransactionId(@Param("transactionId") UUID transactionId);

}
