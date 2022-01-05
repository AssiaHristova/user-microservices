package com.example.userservice.adapters;

import com.example.userservice.models.TransactionDTO;
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

    public List<TransactionDTO> getUserTransactions(int userId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8001/all/?userId={userId}";
        Map<String, String> params = new HashMap<>();
        params.put("userId", String.valueOf(userId));
        ResponseEntity<TransactionDTO[]> entity = restTemplate.getForEntity(url, TransactionDTO[].class, params);
        TransactionDTO[] transactions = entity.getBody();
        return filterTransactions(List.of(transactions), userId);
    }

    public List<TransactionDTO> filterTransactions(List<TransactionDTO> transactions, Integer userId){
        return transactions.stream().filter(transaction -> Objects.equals(transaction.getUserId(), userId)).collect(Collectors.toList());
    }

}