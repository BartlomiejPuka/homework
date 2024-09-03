package com.example.homework.routing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({RouteNotFoundException.class})
    public ResponseEntity handleException(RouteNotFoundException ex, WebRequest req) {
        return ResponseEntity.notFound()
                .build();
    }
}
