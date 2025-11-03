package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

   public boolean deletedUser(Long id){
        Optional<User> userbyid=userRepository.findById(id);
        if(userbyid.isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
   }
    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public Optional<User> getDataById(Long id){
        return userRepository.findById(id);
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public User saveuser(User user){
        return userRepository.save(user);
    }
}
