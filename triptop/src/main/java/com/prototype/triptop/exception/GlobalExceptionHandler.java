package com.prototype.triptop.exception;

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
}
