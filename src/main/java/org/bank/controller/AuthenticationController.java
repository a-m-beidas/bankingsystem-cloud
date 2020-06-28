package org.bank.controller;

import org.bank.model.User;
import org.bank.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody User user) throws Exception {
        String token = authenticationService.authenticate(user);
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    @GetMapping(path= "/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorizationHeader) throws ClassNotFoundException {
        authenticationService.logout(authorizationHeader);
        return new ResponseEntity<>("Log out Successful", HttpStatus.OK);
    }
}
