package com.example.userservice.models;

import com.example.addressservice.Address;
import com.example.transactionservice.Transaction;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class UserDTO {

    @Id
    private int id;

    private String firstName;

    private String lastName;

    private List<Address> addresses;

    private List<Transaction> transactions;
}
