package com.enigmacamp.tokonyadia.dto.response;

import com.enigmacamp.tokonyadia.entity.Customer;
import com.enigmacamp.tokonyadia.entity.TransactionDetail;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private UUID id;
    private LocalDate date;
    private Customer customer;
    private List<TransactionDetail> transactionDetail;
}
