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
        int userId = getUserFromHeader(authorizationHeader);
        userRepository.changeBalanceByAmount(amountToDeposit, userId);
        return userId;
    }

    public int withdraw(float amountToWithdraw, String authorizationHeader) throws ClassNotFoundException {
        int userId = getUserFromHeader(authorizationHeader);
        userRepository.changeBalanceByAmount(amountToWithdraw, userId);
        return userId;
    }

    public int getUserFromHeader(String authorizationHeader) throws ClassNotFoundException {
        String token = authorizationHeader.substring(7);
        return tokenUtility.getUserIdFromToken(token);
    }
}
