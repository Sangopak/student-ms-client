package com.sango.microservices.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CommonExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<Object> handleExceptionDefault(
            Exception ex, WebRequest request) {
        CommonExceptionResponse commonExceptionResponse = new CommonExceptionResponse(new Date(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(commonExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
