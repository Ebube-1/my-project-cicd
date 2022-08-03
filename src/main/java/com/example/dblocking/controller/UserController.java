package com.example.dblocking.controller;

import com.example.dblocking.model.AppUser;
import com.example.dblocking.model.Wallet;
import com.example.dblocking.request.FundWalletRequest;
import com.example.dblocking.request.UserRequest;
import com.example.dblocking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> createUser (@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @GetMapping("/getusers")
    public List<AppUser> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/wallet/{id}")
    public ResponseEntity<String> fundWallet (@PathVariable Long id, @RequestBody FundWalletRequest fundWalletRequest){
        return userService.fundWallet(id, fundWalletRequest);
    }

    @PostMapping("/balance/{id}")
    public ResponseEntity<String> updateWalletBalance (@PathVariable Long id, @RequestBody Wallet wallet){
        return userService.updateWalletBalance(id, wallet);
    }

}
