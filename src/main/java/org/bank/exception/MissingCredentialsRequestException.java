package org.bank.exception;

public class MissingCredentialsRequestException extends RuntimeException {

    public MissingCredentialsRequestException(String message) {
        super(message);
    }
}
