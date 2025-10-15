package com.kiosco.infrastructure.configuration.exceptionHandlers.throwable;

public class BadRequestException extends ProyectException {
    public BadRequestException(String message) {
        super(message);
    }
}
