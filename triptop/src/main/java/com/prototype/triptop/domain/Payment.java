package com.prototype.triptop.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//TODO: change sql to fit this? might be unnecessary if payments dont need to be saved
//@Table("Payment")
public class Payment {
    @Id
    private int paymentId;
    private int amount;
    private String currency;
    private int userId;

    public Payment(int paymentId, int amount, String currency, int userId) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.currency = currency;
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAmountAsString() {
        return String.valueOf(amount);
    }

    public String toString() {
        return "Payment{" + "amount=" + amount + ", currency='" + currency  + ", userId=" + userId + '}';
    }
}
