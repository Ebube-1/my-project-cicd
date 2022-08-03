package com.example.dblocking.service;

import com.example.dblocking.model.AppUser;
import com.example.dblocking.model.Wallet;
import com.example.dblocking.request.FundWalletRequest;
import com.example.dblocking.request.UserRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<String> createUser(UserRequest userRequest);
    ResponseEntity<String> fundWallet(Long id, FundWalletRequest fundWalletRequest);
    ResponseEntity<String> updateWalletBalance(Long id, Wallet wallet);

    List<AppUser> getAllUsers();

}
