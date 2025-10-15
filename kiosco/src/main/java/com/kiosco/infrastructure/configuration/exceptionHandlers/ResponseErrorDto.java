package com.kiosco.infrastructure.configuration.exceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseErrorDto {
    private Integer status;
    private String message;
}
