package org.bank.exception;

public class IllegalJWTException extends RuntimeException {

    public IllegalJWTException(String message) {
        super(message);
    }
}
