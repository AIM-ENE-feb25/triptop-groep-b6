package com.prototype.triptop.repository;

import com.prototype.triptop.domain.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PaymentRepository extends CrudRepository<Payment, Integer> {

//    @Query("SELECT * FROM Betaling WHERE gebruiker_id = :userId")

    //TODO: check if this works
    List<Payment> findPaymentByUserId(int userId);

}