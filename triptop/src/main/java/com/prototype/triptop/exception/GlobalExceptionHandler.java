package com.prototype.triptop.exception;

import org.apache.tomcat.websocket.AuthenticationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {
    public HashMap<String, String> createErrorResponse(String title, String error) {
        HashMap<String, String> errorResponse = new HashMap<>();
        errorResponse.put("title", title);
        errorResponse.put("error", error);
        return errorResponse;
    }

    @ExceptionHandler(InvalidPaymentException.class)
    public ResponseEntity<HashMap<String, String>> handleInvalidPaymentException(InvalidPaymentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createErrorResponse("InvalidPaymentException", ex.getMessage()));
    }

    @ExceptionHandler(PaymentRequestException.class)
    public ResponseEntity<HashMap<String, String>> handlePaymentFailedException(PaymentRequestException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorResponse("PaymentRequestException", ex.getMessage()));
    }

    @ExceptionHandler(InvalidRequestOriginException.class)
    public ResponseEntity<String> handleInvalidRequestOriginException(InvalidRequestOriginException e) {
        return ResponseEntity.status(403).body("Deze actie is niet toegestaan: " + e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(401).body("Gebruiker is niet ingelogd: " + e.getMessage());
    }

    @ExceptionHandler(NoAuthCodeException.class)
    public ResponseEntity<String> handleNoAuthCodeException(@NotNull NoAuthCodeException e) {
        return ResponseEntity.status(401).body("Gebruiker is niet ingelogd: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(500).body("Er is iets fout gegaan: " + e.getMessage());
    }
}
