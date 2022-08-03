package com.example.dblocking.service.impl;

import com.example.dblocking.model.AppUser;
import com.example.dblocking.model.Wallet;
import com.example.dblocking.repository.UserRepository;
import com.example.dblocking.repository.WalletRepository;
import com.example.dblocking.request.FundWalletRequest;
import com.example.dblocking.request.UserRequest;
import com.example.dblocking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Override
    public ResponseEntity<String> createUser(UserRequest userRequest){
        AppUser user = userRepository.findByEmail(userRequest.getEmail());
        if(user != null){
            return new ResponseEntity<>("user already exists", HttpStatus.NOT_FOUND);
        }
        AppUser appUser = AppUser.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .wallet(buildWallet())
                        .build();

        userRepository.save(appUser);

        return new ResponseEntity<>("user and wallet created", HttpStatus.CREATED);
    }

    public Wallet buildWallet (){
        Wallet wallet = Wallet.builder()
                .balance(BigDecimal.valueOf(0.00))
                .createdAt(LocalDateTime.now())
                .build();
        return walletRepository.save(wallet);
    }

    @Override
    public ResponseEntity<String> fundWallet(Long id, FundWalletRequest fundWalletRequest){

        AppUser user = userRepository.findByEmail(fundWalletRequest.getEmail());
        if(user == null){
            return new ResponseEntity<>("user does not exist", HttpStatus.NOT_FOUND);
        }

        Wallet wallet = walletRepository.findById(id).orElse(null);
        if(wallet == null){
            return new ResponseEntity<>("wallet does not exist", HttpStatus.NOT_FOUND);
        }

        wallet.setBalance(fundWalletRequest.getAmount().add(wallet.getBalance()));
        wallet.setCreatedAt(LocalDateTime.now());
        walletRepository.save(wallet);

        return new ResponseEntity<>("funds saved", HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<String> updateWalletBalance(Long id, Wallet wallet) {
        int updateWallet = walletRepository.updateWallet(wallet.getBalance(), wallet.getVersion(), wallet.getId());
        if(updateWallet < 1) return new ResponseEntity<>("please try again",HttpStatus.NOT_FOUND);

        return new ResponseEntity<>("account updated by transaction",HttpStatus.OK);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }


}
