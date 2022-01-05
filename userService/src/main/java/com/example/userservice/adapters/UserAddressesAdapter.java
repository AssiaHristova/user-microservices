package com.example.userservice.adapters;

import java.util.*;
import java.util.stream.Collectors;

import com.example.userservice.models.AddressDTO;
import com.example.userservice.models.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserAddressesAdapter {

    public List<AddressDTO> getUserAddresses(int userId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8002/all/?userId={userId}";
        Map<String, String> params = new HashMap<>();
        params.put("userId", String.valueOf(userId));
        ResponseEntity<AddressDTO[]> entity = restTemplate.getForEntity(url, AddressDTO[].class, params);
        AddressDTO[] addresses = entity.getBody();
        return filterAddresses(List.of(addresses), userId);
    }

    public List<AddressDTO> filterAddresses(List<AddressDTO> addresses, Integer userId){
        return addresses.stream().filter(address -> Objects.equals(address.getUserId(), userId)).collect(Collectors.toList());
    }

    public void createAddresses(UserDTO userDTORequest) {
        List<AddressDTO> addresses = userDTORequest.getAddresses();
        for (AddressDTO address : addresses) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://127.0.0.1:8002";
            ResponseEntity<AddressDTO> response = restTemplate.postForEntity(url, address, AddressDTO.class);
        }
    }
}
