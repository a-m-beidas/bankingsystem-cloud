package org.bank.config;

import org.bank.exception.AuthenticationJWTException;
import org.bank.exception.IllegalJWTException;
import org.bank.exception.MissingCredentialsRequestException;
import org.bank.exception.UserWithNoRolesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MainControllerAdvice {

    Logger logger = LoggerFactory.getLogger(MainControllerAdvice.class);

    //TODO good way to handle exceptions
    @ExceptionHandler
    public ResponseEntity<String> genericHandler(AuthenticationJWTException e) throws Exception {
        logger.warn(e.getMessage());
        return new ResponseEntity<>("Authentication failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<String> genericHandler(IllegalJWTException e) throws Exception {
        return new ResponseEntity<>("Authentication failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler
    public ResponseEntity<String> genericHandler(MissingCredentialsRequestException e) throws Exception {
        logger.warn(e.getMessage());
        return new ResponseEntity<>("Credentials Missing", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler
    public ResponseEntity<String> genericHandler(UserWithNoRolesException e) throws Exception {
        logger.warn(e.getMessage());
        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
