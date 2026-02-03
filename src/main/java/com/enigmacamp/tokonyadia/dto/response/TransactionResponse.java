package com.enigmacamp.tokonyadia.dto.response;

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
    private CustomerResponse customer;
    private List<TransactionDetailResponse> transactionDetailResponses;

    private Double totalPrice;
}
