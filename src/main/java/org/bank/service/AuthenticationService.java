package org.bank.service;

import org.bank.model.CustomUserDetails;
import org.bank.model.User;
import org.bank.security.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManger;

    @Autowired
    TokenUtility tokenUtility;

    @Autowired
    CustomUserDetailsService userDetailsService;

    public String authenticate(User user) throws Exception {
        validateUser(user);
        try {
            authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("Disabled user");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad Credentials");
        }
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        return tokenUtility.generateToken(userDetails);
    }

    public void logout(String authorizationHeader) throws ClassNotFoundException {
        int userId = tokenUtility.getUserIdFromHeader(authorizationHeader);
    }

    private void validateUser(User user) {
        if (user.getUsername() == null) {
            throw new IllegalArgumentException("No username provided");
        }
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("No password provided");
        }
    }
}
