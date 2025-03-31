package com.prototype.triptop.adapter;

import com.prototype.triptop.domain.Payment;

public class StripeAdapter implements PaymentAdapterInterface{
    private String stripeBaseURL;

    //TODO: add curl requests!
    @Override
    public boolean isEnoughMoney(Payment payment, int currentTaxPercentage, double price) {
        return false;
    }

    @Override
    public void processPayment(Payment payment) {

    }

    @Override
    public boolean checkPayment(Payment payment) {
        return false;
    }
}
