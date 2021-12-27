package com.example.transactionservice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepo extends MongoRepository<Transaction, String> {
}
