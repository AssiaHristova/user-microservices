package com.example.userservice;

import com.amazonaws.services.codebuild.model.ResourceNotFoundException;
import com.example.userservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    public void createUser(User userRequest){
        User user = new User();
        user.setId(userRequest.getId());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        userRepo.save(user);
    }

    public User findUserById(int id) throws ResourceNotFoundException {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("Cannot find User with id: " + id);
        } else return user;
    }

    public User updateUser(int id, User userRequest) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User"));
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        return userRepo.save(user);
    }

    public void deleteUser(int id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User"));
        userRepo.delete(user);
    }

    public User getUserById(int id) {
        Optional<User> result = userRepo.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("User");
        }}

}
