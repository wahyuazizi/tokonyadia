package com.enigmacamp.tokonyadia.dto.request;

import com.enigmacamp.tokonyadia.entity.Customer;
import com.enigmacamp.tokonyadia.entity.TransactionDetail;

public record TransactionRequest(
        Customer customerName,
        TransactionDetail transactionDetail
) {

}
