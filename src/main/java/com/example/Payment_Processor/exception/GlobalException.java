package com.example.Payment_Processor.exception;

import jakarta.ws.rs.Produces;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalException {

    @ExceptionHandler(CustomException.class)
    @Produces(MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getStatus().value());
        return new ResponseEntity<>(errorResponse, e.getStatus());
    }
}
