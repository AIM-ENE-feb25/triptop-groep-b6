package com.prototype.triptop.controller;


import com.prototype.triptop.adapter.StripeAdapter;
import com.prototype.triptop.domain.Payment;
import com.prototype.triptop.exception.InvalidPaymentException;
import com.prototype.triptop.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService = new PaymentService();

    // TODO: needs authorization/ authentication
    @PostMapping("/process")
    public ResponseEntity processPayment(@RequestBody Payment payment) {
//        paymentService.pay(payment);
        paymentService.isValid(payment);
        return ResponseEntity.ok(payment.toString());
    }

}
