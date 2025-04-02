package com.prototype.triptop.repository;

import com.prototype.triptop.domain.Payment;
import com.prototype.triptop.rowmapper.PaymentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PaymentRowMapper rowMapper = new PaymentRowMapper();

    public void insertPayment(int amount, String currency, int userId) {
        String sql = "INSERT INTO Betaling (bedrag, valuta, gebruikersId) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, amount, currency, userId);
    }

    public List<Payment> findPaymentByUserId(int userId) {
        String sql = "SELECT * FROM Betaling WHERE gebruikersId = ?";
        List<Payment> payments = jdbcTemplate.queryForList(sql, new Object[]{userId}, rowMapper);
        return payments;
    }


}
