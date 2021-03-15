package com.example.demo.controller;

import com.example.demo.model.Delegation;
import com.example.demo.model.User;
import com.example.demo.service.DelegationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;

import java.util.List;

@RestController
public class SiteController {
    @Autowired

    public SiteController(UserService userService, DelegationService delegationService) {
        this.userService = userService;
        this.delegationService = delegationService;
    }
    private UserService userService;
    private DelegationService delegationService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users")
    public List<User> getAllUsersByRoleName(
            @RequestParam("roleName")String roleName
    ){
        return userService.getAllUsersByRoleName(roleName);
    }

    @GetMapping("/delegations")
    public List<Delegation> getAllDelegations(){
        return delegationService.getAllDelegations();
    }

    @GetMapping("/delegations/byUser")
    public List<Delegation> getAllDelegationsByUserOrderByDateStartDesc(){
        return delegationService.getAllDelegationsOrderByDateStartDesc();
    }

    @GetMapping("/delegations/byUser")
    public List<Delegation> getAllDelegationsByUserOrderByDateStartDesc(
            @RequestParam("userId")int userId
    ){
        return delegationService.getAllDelegationsByUserOrderByDateStartDesc(userId);
    }

    @DeleteMapping("/user/delete")
    public boolean deleteUserById(@RequestParam("userId") int userId){
        return userService.deleteUSerById(userId);
    }

    @DeleteMapping("/delegation/delete")
    public boolean removeDelegation(
            @RequestParam("userId")int userId,
            @RequestParam("delegationId")int delegationId
    ){
       return delegationService.removeDelegation(userId,delegationId);
    }

    @PostMapping("/user/register")
    public User registerUser(
            @RequestParam("companyAddress")String companyAddress,
            @RequestParam("companyNip")String companyNip,
            @RequestParam("name")String name,
            @RequestParam("lastName")String lastName,
            @RequestParam("email")String email,
            @RequestParam("password")String password
    ){
        return userService.registerUser( new User(companyAddress,companyNip,name,lastName,email,password) );
    }

    @PutMapping("/user/changePassword")
    public boolean changePassword(
            @RequestParam("userId")int userId,
            @RequestParam("newPassword")String newPassword
    ){
        return userService.changePassword(userId,newPassword);
    }

    @PostMapping("/delegation")
    public Delegation addDelegation(
        @RequestParam("userId")int userId,
        @RequestParam("dateTimeStart")Timestamp dateTimeStart,
        @RequestParam("dateTimeStop")Timestamp dateTimeStop
    ){
        return delegationService.addDelegation(userId,new Delegation(dateTimeStart,dateTimeStop));
    }


    @PutMapping("/delegation")
    public boolean changeDelegation(
            @RequestParam("delegationId")int delegationId,
            @RequestParam("delegation")Delegation delegation
    ){
        return delegationService.changeDelegation(delegationId,delegation);
    }



}
