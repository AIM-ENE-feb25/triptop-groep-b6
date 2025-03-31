package com.prototype.triptop.adapter;

import com.prototype.triptop.domain.Payment;

public interface PaymentAdapterInterface {
//    boolean isEnoughMoney(Payment payment, int currentTaxPercentage, double price);
    void processPayment(Payment payment);
//    boolean checkPayment(Payment payment);
}
