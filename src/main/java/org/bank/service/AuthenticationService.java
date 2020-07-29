package org.bank.service;

import org.bank.exception.AuthenticationJWTException;
import org.bank.exception.MissingCredentialsRequestException;
import org.bank.model.AuthenticationUserDetails;
import org.bank.model.User;
import org.bank.repository.UserRepository;
import org.bank.security.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManger;

    @Autowired
    TokenUtility tokenUtility;

    @Autowired
    AuthenticationUserDetailsService userDetailsService;

    /**
     * If the user is logged in previously this will log him out
     * @param user
     * @return
     * @throws Exception
     */
    public String authenticate(User user) throws Exception {
        userRepository.login(user.getUsername());
        //TODO check if already logged in
        validateUser(user);
        try {
            authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (DisabledException e) {
            throw new AuthenticationJWTException("Disabled user");
        } catch (BadCredentialsException e) {
            throw new AuthenticationJWTException("Bad Credentials");
        }
        AuthenticationUserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        return tokenUtility.generateToken(userDetails);
    }

    public void logout(String authorizationHeader) throws ClassNotFoundException {
        SecurityContextHolder.clearContext();
        int userId = tokenUtility.getUserIdFromHeader(authorizationHeader);
        userRepository.logout(userId);
    }

    private void validateUser(User user) {
        if (user.getUsername() == null) {
            throw new MissingCredentialsRequestException("No username provided");
        }
        if (user.getPassword() == null) {
            throw new MissingCredentialsRequestException("No password provided");
        }
    }
}
