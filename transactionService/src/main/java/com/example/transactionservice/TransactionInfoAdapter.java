package com.example.transactionservice;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionInfoAdapter {

    public int max_count = 0;

    public String retry() throws Exception {
        return getTransactionInfo();
    }

    public String getTransactionInfo() throws Exception{
        String transactionInfo = null;
        var uri = "http://127.0.0.1:8000/info/generate/id";
        RestTemplate restTemplate = new RestTemplate();
        try{
            transactionInfo = restTemplate.getForObject(uri, String.class);
        }catch (Exception ignored){};

        if (transactionInfo != null){
            max_count = 0;
            return transactionInfo;
        }
        max_count += 1;
        if (max_count < 10){
            return retry();
        }
        throw new Exception("Error");
    }

    }

