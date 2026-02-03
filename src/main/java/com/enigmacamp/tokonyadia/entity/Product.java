package com.enigmacamp.tokonyadia.entity;

import com.enigmacamp.tokonyadia.dto.response.ProductResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "m_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
public class Product extends BaseEntity{

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String productName;
    private Double productPrice;
    private Integer stock;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"product"})
    private List<TransactionDetail> transactionDetails;

    public ProductResponse toResponse(){
        return ProductResponse.builder()
                .id(getId())
                .name(getProductName())
                .price(getProductPrice())
                .stock(getStock())
                .build();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", stock=" + stock +
                '}';
    }
}
