package com.enigmacamp.tokonyadia.dto.request;

public record ProductRequest(
        String productName,
        Double productPrice,
        Integer stock
) {
}
