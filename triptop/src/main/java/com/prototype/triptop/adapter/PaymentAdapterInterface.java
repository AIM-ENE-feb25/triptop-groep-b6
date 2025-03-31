package com.prototype.triptop.adapter;

import com.prototype.triptop.domain.Payment;

import java.io.IOException;

public interface PaymentAdapterInterface {
//    boolean isEnoughMoney(Payment payment, int currentTaxPercentage, double price);
    void processPayment(Payment payment) throws IOException;
//    boolean checkPayment(Payment payment);
}
