package com.example.testsecurity.controller;

import com.example.testsecurity.model.TransactionRequestBody;
import com.example.testsecurity.model.User;
import com.example.testsecurity.security.TokenUtility;
import com.example.testsecurity.model.CustomUserDetails;
import com.example.testsecurity.service.CustomUserDetailsService;
import com.example.testsecurity.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @Autowired
    AuthenticationManager authenticationManger;

    @Autowired
    TransactionService transactionService;

    @Autowired
    TokenUtility tokenUtility;

    @Autowired
    CustomUserDetailsService userDetailsService;


    @PostMapping(path = "/authenticate")
    public ResponseEntity<String> login(@RequestBody User user) throws Exception {
        authenticate(user.getUsername(), user.getPassword());
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = tokenUtility.generateToken(userDetails);
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("Disabled user");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad Credentials");
        }
    }
    private void checkAmountIsPositive(float amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be a positive decimal");
        }
    }

    private void assertEqualZero(int x) {
        if (x != 0) {
            throw new IllegalArgumentException("invalid request body");
        }
    }

    private void assertNotEqualZero(int x) {
        if (x == 0) {
            throw new IllegalArgumentException("invalid request body");
        }
    }
}
