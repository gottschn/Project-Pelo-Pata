package com.kiosco.infrastructure.configuration.exceptionHandlers;


import com.kiosco.infrastructure.configuration.exceptionHandlers.throwable.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<ResponseErrorDto> handleUnauthorizedException(UnauthorizedException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseErrorDto(401, ex.getMessage()));

    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseErrorDto> handleException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseErrorDto(400, ex.getMessage()));
    }
}
