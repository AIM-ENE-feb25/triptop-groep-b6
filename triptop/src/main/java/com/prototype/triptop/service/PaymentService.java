package com.prototype.triptop.service;
import com.prototype.triptop.adapter.PaymentAdapterInterface;
import com.prototype.triptop.adapter.StripeAdapter;
import com.prototype.triptop.domain.Currencies;
import com.prototype.triptop.domain.Payment;
import com.prototype.triptop.exception.InvalidPaymentException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PaymentService {
    private PaymentAdapterInterface adapter = new StripeAdapter();


    public void pay(Payment payment) {
        try {
            adapter.processPayment(payment);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isValid(Payment payment) {
        if (payment.getAmount() <= 0) {
            throw new InvalidPaymentException("Payment amount must be greater than 0: " + payment.getAmount());
        } else if (!isCurrencyValid(payment.getCurrency())) {
            throw new InvalidPaymentException("Payment currency is not valid: " + payment.getCurrency());
        } else {
            return true;
        }
    }

    public boolean isCurrencyValid(String uncheckedCurrency) {
        for (Currencies currency : Currencies.values()) {
            if (currency.name().equals(uncheckedCurrency)) {
                return true;
            }
        }
        return false;
    }
}
