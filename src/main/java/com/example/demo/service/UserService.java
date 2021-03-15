package com.example.demo.service;

import com.example.demo.model.Delegation;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user){return userRepository.save(user); }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public boolean changePassword(int userId,String newPassword){
        Optional<User> temp = userRepository.findById(userId);
        if(temp.isPresent()==true){
            temp.get().setPassword(newPassword);
            userRepository.save(temp.get());
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteUSerById(int userId){
        if( userRepository.existsById(userId)==true ){
            userRepository.deleteById(userId);
            return true;
        }else{
            return false;
        }
    }

    public List<User> getAllUsersByRoleName(String roleName){
        return userRepository.findAll().stream().filter(a->{return a.getRole().toString().equals(roleName);}).collect(Collectors.toList());
    }




}
