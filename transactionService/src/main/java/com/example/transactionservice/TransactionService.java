package com.example.transactionservice;

import com.amazonaws.services.codebuild.model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    public List<Transaction> findAllTransactions() {
        return transactionRepo.findAll();
    }

    public void createTransaction(String id, LocalDateTime localDateTime, Transaction transactionRequest){
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setLocalDateTime(localDateTime);
        transaction.setUserId(transactionRequest.getUserId());
        transaction.setAmount(transactionRequest.getAmount());
        transactionRepo.save(transaction);
    }

    public Transaction updateTransaction(String id, Transaction transactionRequest) {
        Transaction transaction = transactionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction"));
        transaction.setUserId(transactionRequest.getUserId());
        transaction.setAmount(transactionRequest.getAmount());
        return transactionRepo.save(transaction);
    }

    public void deleteTransaction(String id) {
        Transaction transaction = transactionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction"));
        transactionRepo.delete(transaction);
    }

    public Transaction getTransactionById(String id) {
        Optional<Transaction> result = transactionRepo.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("Transaction");
        }

    }

    public List<Transaction> getTransactionsByUserId(Integer userId, Integer count) {
        return transactionRepo.findAll().stream().filter(transaction -> transaction.getUserId() == userId)
        //        .sorted(Comparator.comparing(Transaction::getLocalDateTime).reversed())
                .limit(count)
                .collect(Collectors.toList());

    }
}
