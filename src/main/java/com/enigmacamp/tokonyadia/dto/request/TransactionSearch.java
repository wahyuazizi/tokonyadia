package com.enigmacamp.tokonyadia.dto.request;

import com.enigmacamp.tokonyadia.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionSearch {
    private LocalDate date;
    private Customer customer;
    private Integer minQuantity;
    private Integer maxQuantity;
    private Double minPriceSell;
    private Double maxPriceSell;
}
