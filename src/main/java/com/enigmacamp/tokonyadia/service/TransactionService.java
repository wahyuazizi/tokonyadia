package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.dto.request.TransactionRequest;
import com.enigmacamp.tokonyadia.dto.request.TransactionSearch;
import com.enigmacamp.tokonyadia.dto.response.TransactionResponse;
import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);
    List<Transaction> saveAllTransactions(List<Transaction> transactions);
    Transaction getTransactionById(UUID id);
    Page<TransactionResponse> getTransactions(Pageable pageable, TransactionSearch transactionSearch);
    void cancelTransaction(UUID id);
//    Transaction updateTransaction(Transaction transaction);
}
