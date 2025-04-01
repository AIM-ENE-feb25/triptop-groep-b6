package com.prototype.triptop;

import com.prototype.triptop.identityprovider.InvalidRequestOriginException;
import com.prototype.triptop.identityprovider.NoAuthCodeException;
import com.sun.jdi.request.InvalidRequestStateException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestOriginException.class)
    public ResponseEntity<String> handleInvalidRequestOriginException(InvalidRequestOriginException e) {
        return ResponseEntity.status(403).body("Deze actie is niet toegestaan: " + e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(401).body("Gebruiker is niet ingelogd: " + e.getMessage());
    }

    @ExceptionHandler(NoAuthCodeException.class)
    public ResponseEntity<String> handleNoAuthCodeException(NoAuthCodeException e) {
        return ResponseEntity.status(401).body("Gebruiker is niet ingelogd: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(500).body("Er is iets fout gegaan: " + e.getMessage());
    }
}
