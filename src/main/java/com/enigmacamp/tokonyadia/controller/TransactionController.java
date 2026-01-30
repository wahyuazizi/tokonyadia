package com.enigmacamp.tokonyadia.controller;

import com.enigmacamp.tokonyadia.entity.Transaction;
import com.enigmacamp.tokonyadia.service.TransactionService;
import com.enigmacamp.tokonyadia.utils.constant.ApiUrlConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiUrlConstant.TRANSACTION)
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @PostMapping("/batch")
    public List<Transaction> saveAllTransactions(@RequestBody List<Transaction> transactions) {
        return transactionService.saveAllTransactions(transactions);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable UUID id) {
        return transactionService.getTransactionById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable UUID id) {
        getTransactionById(id);
        transactionService.deleteTransaction(id);
    }

    @PutMapping
    public Transaction updateTransaction(@RequestBody Transaction transaction) {
        getTransactionById(transaction.getId());
        return transactionService.updateTransaction(transaction);
    }
}
