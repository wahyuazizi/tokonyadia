package com.enigmacamp.tokonyadia.dto.response;

import com.enigmacamp.tokonyadia.entity.Product;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetailResponse {
    private UUID id;
    private Product product;
    private Integer quantity;
    private Double priceSell;
    private Double subTotal;
    private Double totalPrice;
}
