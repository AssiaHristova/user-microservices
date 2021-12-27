package com.example.addressservice;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Address {
    @Id
    private Integer id;

    private Integer userId;

    private String address;

}
