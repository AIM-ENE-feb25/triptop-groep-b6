package com.prototype.triptop.repository;

import com.prototype.triptop.domain.Payment;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//TODO: should be "extends"?
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

//    @Query("SELECT * FROM Betaling WHERE gebruiker_id = :userId")
    Payment findPaymentByUserId(int userId);

}