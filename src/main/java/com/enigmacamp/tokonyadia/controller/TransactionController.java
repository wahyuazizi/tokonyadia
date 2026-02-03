package com.enigmacamp.tokonyadia.controller;

import com.enigmacamp.tokonyadia.dto.request.TransactionRequest;
import com.enigmacamp.tokonyadia.dto.request.TransactionSearch;
import com.enigmacamp.tokonyadia.dto.response.PageResponseWrapper;
import com.enigmacamp.tokonyadia.dto.response.TransactionResponse;
import com.enigmacamp.tokonyadia.entity.Transaction;
import com.enigmacamp.tokonyadia.entity.TransactionDetail;
import com.enigmacamp.tokonyadia.service.TransactionDetailService;
import com.enigmacamp.tokonyadia.service.TransactionService;
import com.enigmacamp.tokonyadia.utils.constant.ApiUrlConstant;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiUrlConstant.TRANSACTION)
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionDetailController transactionDetailController;

    public TransactionController(TransactionService transactionService,  TransactionDetailController transactionDetailController) {
        this.transactionService = transactionService;
        this.transactionDetailController = transactionDetailController;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transactionPayload) {
        Transaction transaction = transactionService.saveTransaction(transactionPayload);

        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

    @PostMapping("/batch")
    public List<Transaction> saveAllTransactions(@RequestBody List<Transaction> transactions) {
        return transactionService.saveAllTransactions(transactions);
    }

    @GetMapping
    public ResponseEntity<PageResponseWrapper<TransactionResponse>> getAllTransactions(
            Pageable pageable, @ModelAttribute TransactionSearch transactionSearch
            ) {
        int validatePage = pageable.getPageNumber() > 0 ? pageable.getPageNumber() -1 : 0;
        Pageable validatePageable = PageRequest.of(validatePage, pageable.getPageSize(), pageable.getSort());
        return ResponseEntity.status(HttpStatus.OK).body(
                new PageResponseWrapper<>(transactionService.getTransactions(validatePageable, transactionSearch))
        );
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable UUID id) {
        return transactionService.getTransactionById(id);
    }

    @PutMapping("/{id}")
    public void cancelTransaction(@PathVariable UUID id) {
        Transaction transaction = getTransactionById(id);
        List<TransactionDetail> transactionDetail = transaction.getTransactionDetails();
        transactionService.cancelTransaction(id);
        transactionDetail.forEach(transactionDetail1 -> {
            transactionDetailController.cancelTransactionDetailById(transactionDetail1.getId());
        });
    }
//
//    @PutMapping
//    public Transaction updateTransaction(@RequestBody Transaction transaction) {
//        getTransactionById(transaction.getId());
//        return transactionService.updateTransaction(transaction);
//    }
}
