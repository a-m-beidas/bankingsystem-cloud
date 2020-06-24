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
}
