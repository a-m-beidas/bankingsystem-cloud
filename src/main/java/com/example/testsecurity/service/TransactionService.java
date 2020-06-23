package com.example.testsecurity.service;

import com.example.testsecurity.model.Users;
import com.example.testsecurity.repository.UserRepository;
import com.example.testsecurity.security.config.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtility tokenUtility;

    public int deposit(float amountToDeposit, String authorizationHeader) throws ClassNotFoundException {
        String token = headerToToken(authorizationHeader);
        int userId = getUserFromJWT(token);
        userRepository.changeBalanceByAmount(amountToDeposit, userId);
        return userId;
    }

    public int withdraw(float amountToWithdraw, String authorizationHeader) throws ClassNotFoundException {
        String token = headerToToken(authorizationHeader);
        int userId = getUserFromJWT(token);
        userRepository.changeBalanceByAmount(amountToWithdraw, userId);
        return userId;
    }

    private int getUserFromJWT(String token) throws ClassNotFoundException {
        return (int) tokenUtility.getUserIdFromToken(token);
    }

    private String headerToToken(String header) {
        return header.substring(7);
    }
}
