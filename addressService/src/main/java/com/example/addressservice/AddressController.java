package com.example.addressservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {
    @Autowired
    private final AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("/")
    public String home() {
        return "Hello Addresses";
    }

    @GetMapping("/all")
    public List<Address> getAllAddresses() {
        return addressService.findAllAddresses();
    }

    @GetMapping("/all/{userId}")
    public List<Address> getAllForUser(@PathVariable(name = "userId") Integer userId) {
        return addressService.findAllForUser(userId);
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable(name = "id") Integer id) {
        return addressService.getAddressById(id);
    }

    @PostMapping
    public Address createAddress(@RequestBody Address addressRequest) {
        return addressService.createAddress(addressRequest);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Integer id, @RequestBody Address addressRequest) {
        return addressService.updateAddress(id, addressRequest);
    }

    @DeleteMapping("id")
    public void deleteAddress(@PathVariable Integer id){
        addressService.deleteAddress(id);
    }
}
