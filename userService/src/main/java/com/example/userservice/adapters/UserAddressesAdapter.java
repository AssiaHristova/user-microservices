package com.example.userservice.adapters;

import java.util.*;
import java.util.stream.Collectors;

import com.example.addressservice.Address;
import com.example.userservice.models.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserAddressesAdapter {

    public List<Address> getUserAddresses(int userId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8002/all/?userId={userId}";
        Map<String, String> params = new HashMap<>();
        params.put("userId", String.valueOf(userId));
        ResponseEntity<Address[]> entity = restTemplate.getForEntity(url, Address[].class, params);
        Address[] addresses = entity.getBody();
        return filterAddresses(List.of(addresses), userId);
    }

    public List<Address> filterAddresses(List<Address> addresses, Integer userId){
        return addresses.stream().filter(address -> Objects.equals(address.getUserId(), userId)).collect(Collectors.toList());
    }

    public void createAddresses(UserDTO userDTORequest) {
        List<Address> addresses = userDTORequest.getAddresses();
        for (Address address : addresses) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://127.0.0.1:8002";
            ResponseEntity<Address> response = restTemplate.postForEntity(url, address, Address.class);
        }
    }
}
