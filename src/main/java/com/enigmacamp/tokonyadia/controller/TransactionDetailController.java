package com.enigmacamp.tokonyadia.controller;

import com.enigmacamp.tokonyadia.dto.request.TransactionDetailRequest;
import com.enigmacamp.tokonyadia.dto.response.TransactionDetailResponse;
import com.enigmacamp.tokonyadia.entity.TransactionDetail;
import com.enigmacamp.tokonyadia.service.TransactionDetailService;
import com.enigmacamp.tokonyadia.utils.constant.ApiUrlConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TransactionDetail> createTransactionDetail(@RequestBody TransactionDetail transactionDetailPayload) {
        TransactionDetail transactionDetail = transactionDetailService.saveTransactionDetail(transactionDetailPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionDetail);
    }

    @PostMapping("/batch")
    public List<TransactionDetail> createTransactionDetail(@RequestBody List<TransactionDetail> transactionDetails) {
        return transactionDetailService.saveAllTransactionDetails(transactionDetails);
    }

    @GetMapping
    public List<TransactionDetailResponse> getAllTransactionDetails() {
        return transactionDetailService.getAllTransactionDetails();
    }

    @GetMapping("/{id}")
    public TransactionDetail getTransactionDetailById(@PathVariable UUID id) {
        return transactionDetailService.getTransactionDetailById(id);
    }

    @PutMapping("/{id}")
    public void cancelTransactionDetailById(@PathVariable UUID id) {
        getTransactionDetailById(id);
        transactionDetailService.cancelTransactionDetail(id);
    }
//
//    @PutMapping
//    public TransactionDetail updateTransactionDetail(@RequestBody TransactionDetail transactionDetail) {
//        getTransactionDetailById(transactionDetail.getId());
//        return transactionDetailService.updateTransactionDetail(transactionDetail);
//    }
}
