package com.example.addressservice;

import com.amazonaws.services.codebuild.model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;

    public List<Address> findAllForUser(Integer userId){
        return addressRepo.findAll().stream().filter(address -> Objects.equals(address.getUserId(), userId)).collect(Collectors.toList());
    }
    public List<Address> findAllAddresses(){
        return addressRepo.findAll();
    }

    public Address createAddress(Address addressRequest){
        Address address = new Address();
        address.setId(addressRequest.getId());
        address.setUserId(addressRequest.getUserId());
        address.setAddress(addressRequest.getAddress());
        return addressRepo.save(address);
    }

    public Address updateAddress(Integer id, Address addressRequest) {
        Address address = addressRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address"));
        address.setUserId(addressRequest.getUserId());
        address.setAddress(addressRequest.getAddress());
        return addressRepo.save(address);
    }

    public void deleteAddress(Integer id) {
        Address address = addressRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address"));
        addressRepo.delete(address);
    }

    public Address getAddressById(Integer id) {
        Optional<Address> result = addressRepo.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("Address");
        }}
}
