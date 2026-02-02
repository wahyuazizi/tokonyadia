package com.enigmacamp.tokonyadia.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearch {
    private String productName;
    private Double minPrice;
    private Double maxPrice;
    private Integer stock;
}
