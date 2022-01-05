package com.example.userservice.models;

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

    private List<AddressDTO> addresses;

    private List<TransactionDTO> transactions;
}
