package com.example.testsecurity.controller;

import com.example.testsecurity.model.Users;
import com.example.testsecurity.security.config.TokenUtility;
import com.example.testsecurity.security.model.CustomUserDetails;
import com.example.testsecurity.security.service.CustomUserDetailsService;
import com.example.testsecurity.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @RequestMapping(path = "/transactions/deposit", method = RequestMethod.POST)
    public ResponseEntity<String> deposit(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Map<String, String> requestBody) throws ClassNotFoundException {
        float amount = Float.parseFloat(requestBody.get("amount"));
        int userId = transactionService.deposit(amount, authorizationHeader);
        return new ResponseEntity<String>("Deposit to user " + userId + " if amount " + amount, HttpStatus.OK);
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<String> login(@RequestBody Users user) throws Exception {
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
}
