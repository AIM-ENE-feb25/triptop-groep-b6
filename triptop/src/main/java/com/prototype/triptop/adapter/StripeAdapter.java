package com.prototype.triptop.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prototype.triptop.domain.Payment;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.net.URL;

public class StripeAdapter implements PaymentAdapterInterface {
    @Value("${STRIPE_API_KEY}")
    private String stripeApiKey;
    private final String stripeUrl = "https://api.stripe.com/v1/payment_intents";


    ObjectMapper mapper = new ObjectMapper();
    //TODO: add curl requests!

    @Override
    public void processPayment(Payment payment) throws IOException {
        URL url = new URL(stripeUrl);
        //TODO: finish this (https://www.baeldung.com/java-http-request)
    }


    //TODO: is not necessary in adapter?
//    @Override
//    public boolean isEnoughMoney(Payment payment, int currentTaxPercentage, double price) {
//        return false;
//    }

    //TODO: is not necessary in adapter?
//    @Override
//    public boolean checkPayment(Payment payment) {
//        return false;
//    }
}