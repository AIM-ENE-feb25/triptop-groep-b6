package com.prototype.triptop.domain;

import java.math.BigDecimal;

public class Hotel {
    private final String name;
    private final String location;
    private final String latitude;
    private final String longitude;
    private final String checkinFromTime;
    private final String checkinUntilTime;
    private final String checkoutFromTime;
    private final String checkoutUntilTime;
    private final double reviewScore;
    private final String reviewScoreWord;
    private final int reviewCount;
    private final String proposedAccommodation;
    private final BigDecimal priceDetailsGross;
    private final BigDecimal priceDetailsInfo;

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

    public double getReviewScore() {
        return reviewScore;
    }

    public String getCheckoutUntilTime() {
        return checkoutUntilTime;
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
