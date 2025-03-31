package com.prototype.triptop.domain;

public class Payment {
    private double amount;
    private String currency;
    private int userId;

    public Payment(double amount, String currency, int userId) {
        this.amount = amount;
        this.currency = currency;
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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
}
