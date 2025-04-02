package com.prototype.triptop.repository;

import com.prototype.triptop.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

    //TODO: check if this works
//    @Query("SELECT * FROM Betaling WHERE gebruiker_id = :userId")
    List<Payment> findPaymentByUserId(int userId);

    @Query(
            "INSERT INTO Betaling (amount, currency, userId) " +
                    "VALUES (:amount, :currency, :userId)"
    )
    void insertPayment(@Param("amount") int amount, @Param("currency") String currency, @Param("userId") int userId);
}
