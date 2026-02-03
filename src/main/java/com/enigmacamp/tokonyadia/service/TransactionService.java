package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.dto.request.TransactionRequest;
import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.entity.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);
    List<Transaction> saveAllTransactions(List<Transaction> transactions);
    Transaction getTransactionById(UUID id);
    List<Transaction> getTransactions();
    void cancelTransaction(UUID id);
//    Transaction updateTransaction(Transaction transaction);
}
