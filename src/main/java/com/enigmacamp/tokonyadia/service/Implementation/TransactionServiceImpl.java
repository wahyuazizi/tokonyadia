package com.enigmacamp.tokonyadia.service.Implementation;

import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.entity.Transaction;
import com.enigmacamp.tokonyadia.repository.TransactionRepository;
import com.enigmacamp.tokonyadia.service.TransactionService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> saveAllTransactions(List<Transaction> transactions) {
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
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public void deleteTransaction(UUID id) {
        getTransactionById(id);
        transactionRepository.deleteById(id);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        getTransactionById(transaction.getId());
        return saveTransaction(transaction);
    }
}
