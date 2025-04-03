package com.prototype.triptop.domain;

import java.math.BigDecimal;

public class Hotel {
    private String name;
    private String location;
    private String latitude;
    private String longitude;
    private String checkinFromTime;
    private String checkinUntilTime;
    private String checkoutFromTime;
    private String checkoutUntilTime;
    private double reviewScore;
    private String reviewScoreWord;
    private int reviewCount;
    private String proposedAccommodation;
    private BigDecimal priceDetailsGross;
    private BigDecimal priceDetailsInfo;

    public Hotel(String name, String location, String latitude, String longitude,
                 String checkinFromTime, String checkinUntilTime, String checkoutFromTime,
                 String checkoutUntilTime, double reviewScore, String reviewScoreWord,
                 int reviewCount, String proposedAccommodation, BigDecimal priceDetailsGross,
                 BigDecimal priceDetailsInfo) {
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.checkinFromTime = checkinFromTime;
        this.checkinUntilTime = checkinUntilTime;
        this.checkoutFromTime = checkoutFromTime;
        this.checkoutUntilTime = checkoutUntilTime;
        this.reviewScore = reviewScore;
        this.reviewScoreWord = reviewScoreWord;
        this.reviewCount = reviewCount;
        this.proposedAccommodation = proposedAccommodation;
        this.priceDetailsGross = priceDetailsGross;
        this.priceDetailsInfo = priceDetailsInfo;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getCheckinFromTime() {
        return checkinFromTime;
    }

    public String getCheckinUntilTime() {
        return checkinUntilTime;
    }

    public String getCheckoutFromTime() {
        return checkoutFromTime;
    }

    public String getCheckoutUntilTime() {
        return checkoutUntilTime;
    }

    public double getReviewScore() {
        return reviewScore;
    }

    public String getReviewScoreWord() {
        return reviewScoreWord;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public String getProposedAccommodation() {
        return proposedAccommodation;
    }

    public BigDecimal getPriceDetailsGross() {
        return priceDetailsGross;
    }

    public BigDecimal getPriceDetailsInfo() {
        return priceDetailsInfo;
    }
}
