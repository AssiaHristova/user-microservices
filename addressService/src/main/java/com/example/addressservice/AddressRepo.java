package com.example.addressservice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepo extends MongoRepository<Address, Integer> {

}
