package com.prototype.triptop.controller;

import com.prototype.triptop.domain.Payment;
import com.prototype.triptop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // TODO: needs authorization/ authentication?
    @PostMapping("/process")
    public ResponseEntity<?> processPayment(@RequestBody Payment payment) {
        return paymentService.pay(payment);
    }
}

