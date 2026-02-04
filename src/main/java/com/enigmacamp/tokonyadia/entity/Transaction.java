package com.enigmacamp.tokonyadia.entity;

import com.enigmacamp.tokonyadia.dto.response.TransactionResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "trx_purchase")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
//@SQLDelete(sql = "UPDATE trx_purchase SET deleted = true WHERE id = ?")
//@SQLRestriction("delete = false")
public class Transaction{
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("transaction")
    private Customer customer;

    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("transaction")
    private List<TransactionDetail> transactionDetails;

    public TransactionResponse toResponse(){
        return TransactionResponse.builder()
                .id(getId())
                .date(getDate())
                .customer(getCustomer().toResponse())
                .transactionDetail(getTransactionDetails().stream().map(TransactionDetail::toResponse).toList())
                .build();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", customer=" + customer +
                '}';
    }
}
