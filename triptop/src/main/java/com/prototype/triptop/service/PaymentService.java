package com.prototype.triptop.service;
import com.prototype.triptop.adapter.PaymentAdapterInterface;
import com.prototype.triptop.adapter.StripeAdapter;
import com.prototype.triptop.domain.Payment;
import com.prototype.triptop.exception.InvalidPaymentException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PaymentService {
    private PaymentAdapterInterface adapter = new StripeAdapter();

    private String currency = "EUR";

    public void pay(Payment payment) {
        adapter.processPayment(payment);
    }

    public void isValid(Payment payment) {
        if (payment.getAmount() <= 0) {
            throw new InvalidPaymentException("Payment amount must be greater than 0: " + payment.getAmount());
        }

    }
}
