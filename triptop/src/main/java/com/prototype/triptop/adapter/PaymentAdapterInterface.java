package com.prototype.triptop.adapter;

import com.prototype.triptop.domain.Payment;

public interface PaymentAdapterInterface {
    public boolean isEnoughMoney(Payment payment, int currentTaxPercentage, double price);
    public void processPayment(Payment payment);
    public boolean checkPayment(Payment payment);
}
