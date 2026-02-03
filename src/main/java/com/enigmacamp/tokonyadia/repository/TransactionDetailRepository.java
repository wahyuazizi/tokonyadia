package com.enigmacamp.tokonyadia.repository;

import com.enigmacamp.tokonyadia.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, UUID> {
    @Query("SELECT (td.priceSell*td.quantity) AS subTotal FROM TransactionDetail td ")
    List<Double> getSubTotal();

    @Query("SELECT SUM (td.priceSell*td.quantity) AS total FROM TransactionDetail td WHERE td.id =?1")
    Double getTotal(UUID id);
}
