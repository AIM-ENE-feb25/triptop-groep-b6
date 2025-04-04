package com.prototype.triptop.service;

import com.prototype.triptop.adapter.PaymentAdapterInterface;
import com.prototype.triptop.adapter.StripeAdapter;
import com.prototype.triptop.domain.Currencies;
import com.prototype.triptop.domain.Payment;
import com.prototype.triptop.exception.InvalidPaymentException;
import com.prototype.triptop.exception.PaymentRequestException;
import com.prototype.triptop.repository.PaymentDAO;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentAdapterInterface adapter = new StripeAdapter();
    private PaymentDAO paymentDAO;
    private AuthService authService;

    @Autowired
    public PaymentService(PaymentDAO paymentDAO, AuthService authService) {
        this.paymentDAO = paymentDAO;
        this.authService = authService;
    }

    public ResponseEntity pay(Payment payment) {
        verifyPayment(payment);
        return handlePaymentRequest(payment);
    }

    //Sends payment to adapter, also handles failed payments (e.g. external api is down)
    private ResponseEntity<String> handlePaymentRequest(Payment payment) {
        try {
            ResponseEntity<String> response = adapter.processPayment(payment);
            if (!authService.verifyToken(payment.getToken())) throw new AuthenticationException("Token is invalid");
            int userID = authService.fetchUserIDFromToken(payment.getToken());
            if (response.getStatusCode().is2xxSuccessful()) { //Post success
                paymentDAO.insertPayment(payment.getAmount(), payment.getCurrency(), userID);
                return ResponseEntity.ok(response.getBody());
            } else { //Post worked, but wasnt 200 code
                throw new PaymentRequestException("Unexpected response code: " + response.getStatusCode());
            }
        } catch (Exception e) { //Post failed
            //TODO: fix this, may accidentally be activated by the repository functions
            throw new PaymentRequestException("Payment request failed: " + e.getMessage());
        }
    }

    //TODO: should check if user exists
    public void verifyPayment(Payment payment) {
        if (payment.getAmount() < 50) {
            throw new InvalidPaymentException("Amount must be 50 or more");
        } else if (!isCurrencyValid(payment.getCurrency())) {
            throw new InvalidPaymentException("Currency is invalid: " + payment.getCurrency());
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
