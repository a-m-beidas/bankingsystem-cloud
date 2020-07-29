package org.bank.exception;

public class AuthenticationJWTException extends AbstractJWTException {

    public AuthenticationJWTException(String message, String token) {
        super(message, token);
    }
}
