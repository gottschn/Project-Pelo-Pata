package com.kiosco.infrastructure.configuration.exceptionHandlers.throwable;

public abstract class ProyectException extends RuntimeException {
    protected ProyectException(String message) { super(message);}
}
