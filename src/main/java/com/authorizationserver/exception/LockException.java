package com.authorizationserver.exception;

public class LockException extends Exception {
    private final String code;
    private final String message;
    public LockException(String code, String message) {
        super(code+"-"+message);
        this.code = code;
        this.message = message;
    }
}
