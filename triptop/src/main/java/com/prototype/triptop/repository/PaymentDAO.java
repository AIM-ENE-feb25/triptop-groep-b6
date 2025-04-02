package com.prototype.triptop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertPayment(int amount, String currency, int userId) {
        String sql = "INSERT INTO Betaling (bedrag, valuta, gebruikersId) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, amount, currency, userId);
    }
}
