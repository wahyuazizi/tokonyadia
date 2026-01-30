package com.enigmacamp.tokonyadia.service.Implementation;

import com.enigmacamp.tokonyadia.entity.TransactionDetail;
import com.enigmacamp.tokonyadia.repository.TransactionDetailRepository;
import com.enigmacamp.tokonyadia.service.TransactionDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionDetailImpl implements TransactionDetailService {

    private final TransactionDetailRepository transactionDetailRepository;

    public TransactionDetailImpl(TransactionDetailRepository transactionDetailRepository) {
        this.transactionDetailRepository = transactionDetailRepository;
    }

    @Override
    public TransactionDetail saveTransactionDetail(TransactionDetail transactionDetail) {
        return transactionDetailRepository.save(transactionDetail);
    }

    @Override
    public List<TransactionDetail> saveAllTransactionDetails(List<TransactionDetail> transactionDetails) {
        return transactionDetailRepository.saveAll(transactionDetails);
    }

    @Override
    public TransactionDetail getTransactionDetailById(UUID id) {
        return transactionDetailRepository.findById(id).orElse(null);
    }

    @Override
    public List<TransactionDetail> getAllTransactionDetails() {
        return transactionDetailRepository.findAll();
    }

    @Override
    public void deleteTransactionDetail(UUID id) {
        getTransactionDetailById(id);
        transactionDetailRepository.deleteById(id);
    }

    @Override
    public TransactionDetail updateTransactionDetail(TransactionDetail transactionDetail) {
        getTransactionDetailById(transactionDetail.getId());
        return saveTransactionDetail(transactionDetail);
    }
}
