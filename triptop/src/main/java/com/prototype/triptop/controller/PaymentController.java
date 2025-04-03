package com.prototype.triptop.controller;

import com.prototype.triptop.domain.Payment;
import com.prototype.triptop.repository.PaymentDAO;
import com.prototype.triptop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentDAO paymentDAO;

    @Autowired
    public PaymentController(PaymentService paymentService, PaymentDAO paymentDAO) {
        this.paymentService = paymentService;
        this.paymentDAO = paymentDAO;
    }

    @PostMapping("/process")
    public ResponseEntity<?> processPayment(@RequestBody Payment payment) {
        return paymentService.pay(payment);
    }

    @GetMapping("/all")
    public List<Payment> getAllPayments() {
        return paymentDAO.getAllPayments();
    }
}

