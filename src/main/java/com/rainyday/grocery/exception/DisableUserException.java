package com.rainyday.grocery.exception;

public class DisableUserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DisableUserException(String msg) {
        super(msg);
    }
}