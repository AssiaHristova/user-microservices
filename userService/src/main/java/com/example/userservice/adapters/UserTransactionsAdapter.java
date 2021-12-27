package com.example.userservice.adapters;

import com.example.transactionservice.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserTransactionsAdapter {

    public List<Transaction> getUserTransactions(int userId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8001/all/?userId={userId}";
        Map<String, String> params = new HashMap<>();
        params.put("userId", String.valueOf(userId));
        ResponseEntity<Transaction[]> entity = restTemplate.getForEntity(url, Transaction[].class, params);
        Transaction[] transactions = entity.getBody();
        return filterTransactions(List.of(transactions), userId);
    }

    public List<Transaction> filterTransactions(List<Transaction> transactions, Integer userId){
        return transactions.stream().filter(transaction -> Objects.equals(transaction.getUserId(), userId)).collect(Collectors.toList());
    }

}