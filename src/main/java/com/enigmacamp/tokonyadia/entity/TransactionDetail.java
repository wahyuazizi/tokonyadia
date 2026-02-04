package com.enigmacamp.tokonyadia.entity;

import com.enigmacamp.tokonyadia.dto.response.TransactionDetailResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "trx_purchase_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
public class TransactionDetail{

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    @JsonIgnoreProperties({"transactionDetails"})
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"transactionDetails"})
    private Product product;

    private Integer quantity;
    private Double priceSell;

    public TransactionDetailResponse toResponse(){
        return TransactionDetailResponse.builder()
                .id(getId())
                .product(getProduct().toResponse())
                .quantity(getQuantity())
                .priceSell(getPriceSell())
                .build();
    }

}
