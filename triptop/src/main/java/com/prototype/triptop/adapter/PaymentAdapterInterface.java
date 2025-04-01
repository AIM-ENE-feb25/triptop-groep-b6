package com.prototype.triptop.adapter;

import com.prototype.triptop.domain.Payment;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface PaymentAdapterInterface {
    ResponseEntity<String> processPayment(Payment payment) throws IOException;
}
