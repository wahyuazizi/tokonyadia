package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.entity.TransactionDetail;

import java.util.List;
import java.util.UUID;

public interface TransactionDetailService {
    TransactionDetail saveTransactionDetail(TransactionDetail transactionDetail);
    List<TransactionDetail> saveAllTransactionDetails(List<TransactionDetail> transactionDetails);
    TransactionDetail getTransactionDetailById(UUID id);
    List<TransactionDetail> getAllTransactionDetails();
    void deleteTransactionDetail(UUID id);
    TransactionDetail updateTransactionDetail(TransactionDetail transactionDetail);
}
