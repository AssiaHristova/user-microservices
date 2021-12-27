package com.example.userservice;

import com.example.addressservice.Address;
import com.example.transactionservice.Transaction;
import com.example.userservice.adapters.UserAddressesAdapter;
import com.example.userservice.adapters.UserTransactionsAdapter;
import com.example.userservice.models.User;
import com.example.userservice.models.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    @Resource
    private UserAddressesAdapter userAddressesAdaptor;

    @Resource
    private UserTransactionsAdapter userTransactionsAdaptor;

    @Autowired
    private ModelMapper modelMapper;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "Hello Users";
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Integer id) {
        User user = userService.findUserById(id);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        List<Address> userAddresses = userAddressesAdaptor.getUserAddresses(id);
        List<Transaction> userTransactions = userTransactionsAdaptor.getUserTransactions(id);
        userDTO.setAddresses(userAddresses);
        userDTO.setTransactions(userTransactions);
        return userDTO;
    }

    @PostMapping
    public User createUser(@RequestBody UserDTO userDTORequest) throws IOException {
        User user = modelMapper.map(userDTORequest, User.class);
        userService.createUser(user);
        userAddressesAdaptor.createAddresses(userDTORequest);
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable(name = "id")Integer id, @RequestBody User userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") Integer id) {
        userService.deleteUser(id);
    }
}
