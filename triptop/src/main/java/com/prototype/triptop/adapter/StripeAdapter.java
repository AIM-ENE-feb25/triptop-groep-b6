package com.prototype.triptop.adapter;

import com.prototype.triptop.TriptopPrototypeApplication;
import com.prototype.triptop.domain.Payment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class StripeAdapter implements PaymentAdapterInterface {

    private final String STRIPE_API_KEY = TriptopPrototypeApplication.dotenv.get("STRIPE_API_KEY");
    private final String STRIPE_URL = "https://api.stripe.com/v1/payment_intents";

    @Override
    public ResponseEntity<String> processPayment(Payment payment) {
        RestTemplate template = new RestTemplate();
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("amount", String.valueOf(payment.getAmount()));
        requestBody.add("currency", payment.getCurrency());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + STRIPE_API_KEY);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = template.postForEntity(STRIPE_URL, requestEntity, String.class);

        System.out.println("Stripe response: " + response.getStatusCode());
        return response;
    }
}