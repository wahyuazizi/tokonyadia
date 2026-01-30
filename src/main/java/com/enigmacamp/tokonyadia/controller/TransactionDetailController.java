package com.enigmacamp.tokonyadia.controller;

import com.enigmacamp.tokonyadia.entity.TransactionDetail;
import com.enigmacamp.tokonyadia.service.TransactionDetailService;
import com.enigmacamp.tokonyadia.utils.constant.ApiUrlConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiUrlConstant.TRANSACTION_DETAIL)
public class TransactionDetailController {
    private final TransactionDetailService  transactionDetailService;

    public TransactionDetailController(TransactionDetailService transactionDetailService) {
        this.transactionDetailService = transactionDetailService;
    }

    @PostMapping
    public TransactionDetail createTransactionDetail(@RequestBody TransactionDetail transactionDetail) {
        return transactionDetailService.saveTransactionDetail(transactionDetail);
    }

    @PostMapping("/batch")
    public List<TransactionDetail> createTransactionDetail(@RequestBody List<TransactionDetail> transactionDetails) {
        return transactionDetailService.saveAllTransactionDetails(transactionDetails);
    }

    @GetMapping
    public List<TransactionDetail> getAllTransactionDetails() {
        return transactionDetailService.getAllTransactionDetails();
    }

    @GetMapping("/{id}")
    public TransactionDetail getTransactionDetailById(@PathVariable UUID id) {
        return transactionDetailService.getTransactionDetailById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTransactionDetailById(@PathVariable UUID id) {
        getTransactionDetailById(id);
        transactionDetailService.deleteTransactionDetail(id);
    }

    @PutMapping
    public TransactionDetail updateTransactionDetail(@RequestBody TransactionDetail transactionDetail) {
        getTransactionDetailById(transactionDetail.getId());
        return transactionDetailService.updateTransactionDetail(transactionDetail);
    }
}
