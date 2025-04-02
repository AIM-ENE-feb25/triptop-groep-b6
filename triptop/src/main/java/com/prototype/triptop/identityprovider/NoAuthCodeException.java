package com.prototype.triptop.identityprovider;

public class NoAuthCodeException extends Exception {
    public NoAuthCodeException(String m) {
        super(m);
    }
}
