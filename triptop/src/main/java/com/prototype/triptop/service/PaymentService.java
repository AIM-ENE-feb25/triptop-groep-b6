package com.prototype.triptop.service;

import com.prototype.triptop.adapter.PaymentAdapterInterface;
import com.prototype.triptop.adapter.StripeAdapter;
import com.prototype.triptop.domain.Currencies;
import com.prototype.triptop.domain.Payment;
import com.prototype.triptop.exception.InvalidPaymentException;
import com.prototype.triptop.exception.PaymentRequestException;
import com.prototype.triptop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentAdapterInterface adapter = new StripeAdapter();
    private PaymentRepository paymentRepository; //WIP

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public ResponseEntity pay(Payment payment) {
        verifyPayment(payment);
        return handlePaymentRequest(payment);
    }

    //Sends payment to adapter, also handles failed payments (e.g. external api is down)
    private ResponseEntity<String> handlePaymentRequest(Payment payment) {
        try {
            ResponseEntity<String> response = adapter.processPayment(payment);
            if (response.getStatusCode().is2xxSuccessful()) { //Post success
//                    TODO: Saving doesnt work yet, need to fix sql
//                    paymentRepository.save(payment);
//                    paymentRepository.findPaymentByUserId(payment.getUserId());
                return ResponseEntity.ok(response.getBody());
            } else { //Post worked, but wasnt 200 code
                throw new PaymentRequestException("Unexpected response code: " + response.getStatusCode());
            }
        } catch (Exception e) { //Post failed
            throw new PaymentRequestException("Payment request failed: " + e.getMessage());
        }
    }

    //TODO: should check if user exists
    public void verifyPayment(Payment payment) {
        if (payment.getAmount() <= 0) {
            throw new InvalidPaymentException("Amount must be greater than 0");
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
