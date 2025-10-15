package com.kiosco.infrastructure.configuration.exceptionHandlers.throwable;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
