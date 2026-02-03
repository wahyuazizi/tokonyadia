package com.enigmacamp.tokonyadia.dto.request;

import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.entity.Transaction;

import java.util.UUID;

public record TransactionDetailRequest(
        Integer quantity,
        Product productId,
        Double priceSell,
        Transaction transactionId
) {
}
