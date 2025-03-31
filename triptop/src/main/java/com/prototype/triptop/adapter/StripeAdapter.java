package com.prototype.triptop.adapter;

import com.prototype.triptop.domain.Payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StripeAdapter implements PaymentAdapterInterface {
    private String stripeBaseURL;

    public StripeAdapter() {
    }

    //TODO: add curl requests!

    //TODO: is not necessary in adapter?
//    @Override
//    public boolean isEnoughMoney(Payment payment, int currentTaxPercentage, double price) {
//        return false;
//    }

    @Override
    public void processPayment(Payment payment) {
    }

    //TODO: is not necessary in adapter?
//    @Override
//    public boolean checkPayment(Payment payment) {
//        return false;
//    }
}