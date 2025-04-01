package com.prototype.triptop.service;

import com.prototype.triptop.adapter.PaymentAdapterInterface;
import com.prototype.triptop.adapter.StripeAdapter;
import com.prototype.triptop.domain.Currencies;
import com.prototype.triptop.domain.Payment;
import com.prototype.triptop.exception.InvalidPaymentException;
import com.prototype.triptop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private PaymentAdapterInterface adapter = new StripeAdapter();
    private PaymentRepository repository;


    @Autowired
    public PaymentService(PaymentRepository repository, PaymentRepository paymentRepository) {
        this.repository = repository;
        this.paymentRepository = paymentRepository;
    }

    public ResponseEntity pay(Payment payment) {
        if (isValid(payment)) {
            try {
                ResponseEntity<String> response = adapter.processPayment(payment);
                if (response.getStatusCode().is2xxSuccessful()) { //Post success
//                    Saving doesnt work yet, need to fix sql
//                    paymentRepository.save(payment);
//                    paymentRepository.findPaymentByUserId(payment.getUserId());
                    return ResponseEntity.ok(response.getBody());
                } else { //Post worked, but wasnt 200 code
                    return ResponseEntity.internalServerError().body(createErrorResponse("Something went wrong", String.valueOf(response.getStatusCode()), response.getBody()));
                }
            } catch (Exception e) { //Post failed
                return ResponseEntity.internalServerError().body(createErrorResponse("Internal server error", "none", e.getMessage()));

            }
        } else {
//            throw new InvalidPaymentException("Invalid payment: " + payment.toString());
            return ResponseEntity.badRequest().body(createErrorResponse("Payment is not valid", "none", payment.toString()));
        }
    }

    public HashMap<String, String> createErrorResponse(String message, String statusCode, String error) {
        HashMap<String, String> errorResponse = new HashMap<>();
        errorResponse.put("errorMessage", message);
        errorResponse.put("receivedStatusCode", statusCode);
        errorResponse.put("error", error);
        return errorResponse;
    }

    //TODO: should check if user exists
    public boolean isValid(Payment payment) {
        if (payment.getAmount() <= 0) {
            return false;
        } else if (!isCurrencyValid(payment.getCurrency())) {
            return false;
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
