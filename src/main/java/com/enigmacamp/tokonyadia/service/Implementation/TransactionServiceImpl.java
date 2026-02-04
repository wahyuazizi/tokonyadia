package com.enigmacamp.tokonyadia.service.Implementation;

import com.enigmacamp.tokonyadia.dto.request.TransactionSearch;
import com.enigmacamp.tokonyadia.dto.response.TransactionResponse;
import com.enigmacamp.tokonyadia.entity.Transaction;
import com.enigmacamp.tokonyadia.entity.TransactionDetail;
import com.enigmacamp.tokonyadia.repository.TransactionRepository;
import com.enigmacamp.tokonyadia.service.TransactionService;
import com.enigmacamp.tokonyadia.spesification.TransactionSpecification;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionDetailServiceImpl transactionDetailServiceImpl;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionDetailServiceImpl transactionDetailServiceImpl) {
        this.transactionRepository = transactionRepository;
        this.transactionDetailServiceImpl = transactionDetailServiceImpl;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Transaction saveTransaction(Transaction transactionPayload) {
        transactionPayload.setDate(LocalDate.now());

        Transaction transaction = transactionRepository.save(transactionPayload);
        List<TransactionDetail> transactionDetails = transactionPayload.getTransactionDetails();

        transactionDetails.forEach(p->{
            p.setTransaction(transaction);
            transactionDetailServiceImpl.saveTransactionDetail(p);
        });

        return transaction;
    }

    @Override
    public List<Transaction> saveAllTransactions(List<Transaction> transactions) {
        List<Transaction> transactionAccepted = transactionRepository.saveAll(transactions);

        transactionAccepted.forEach(details -> {
            List<TransactionDetail> transactionDetails = details.getTransactionDetails();
            transactionDetails.forEach(p->{
                p.setTransaction(details);
                transactionDetailServiceImpl.saveTransactionDetail(p);
            })   ;
        }
                );
        return transactionRepository.saveAll(transactions);
    }

    @Override
    public Transaction getTransactionById(UUID id) {
        try {
            if(!transactionRepository.existsById(id)){
                throw new ChangeSetPersister.NotFoundException();
            }
            return transactionRepository.getReferenceById(id);
        }catch (ChangeSetPersister.NotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<TransactionResponse> getTransactions(Pageable pageable, TransactionSearch transactionSearch) {
        Specification<Transaction> transactionSpecification = TransactionSpecification.getTransactionSpecification(transactionSearch);

        return transactionRepository.findAll(transactionSpecification, pageable).map(trx->

                    TransactionResponse.builder()
                    .id(trx.getId())
                    .date(trx.getDate())
                    .customer(trx.getCustomer().toResponse())
                    .transactionDetail(transactionDetailServiceImpl.getAllTransactionDetails())
                    .totalPrice(transactionRepository.getTotalByTransactionId(trx.getId())).build()
        );
    }

    @Override
    @Transactional
    public void cancelTransaction(UUID id) {
        Transaction transaction = getTransactionById(id);
        transactionRepository.save(transaction);
    }
//
//    @Override
//    public Transaction updateTransaction(Transaction transaction) {
//        getTransactionById(transaction.getId());
//        return saveTransaction(transaction);
//    }
}
