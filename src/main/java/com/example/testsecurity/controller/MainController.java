package com.example.testsecurity.controller;

import com.example.testsecurity.model.Users;
import com.example.testsecurity.repository.UserRepository;
import com.example.testsecurity.security.config.TokenUtility;
import com.example.testsecurity.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MainController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(path = "/transaction/", method = RequestMethod.POST)
    public ResponseEntity<String> deposit(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Map<String, String> requestBody) throws ClassNotFoundException {
        float amount = Float.parseFloat(requestBody.get("amount"));
        int userId = transactionService.deposit(amount, authorizationHeader);
        return new ResponseEntity<String>("Deposit to user " + userId + " if amount " + amount, HttpStatus.OK);
    }
}
