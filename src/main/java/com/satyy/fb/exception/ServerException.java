package com.satyy.fb.exception;

public class ServerException extends Exception {
    private static final long serialVersionUID = 1L;

    public ServerException(final String message) {
        super(message);
    }
}
