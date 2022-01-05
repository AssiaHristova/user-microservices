package com.example.userservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class AddressDTO {
    @Id
    private Integer id;

    private Integer userId;

    private String address;
}
