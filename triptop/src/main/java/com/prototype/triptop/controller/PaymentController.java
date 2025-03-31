package com.prototype.triptop.controller;


import com.prototype.triptop.domain.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @PostMapping("/process")
    public ResponseEntity processPayment(@RequestBody Payment payment) {
        return ResponseEntity.ok(payment.toString());
    }

}
