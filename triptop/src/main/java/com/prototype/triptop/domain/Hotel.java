package com.prototype.triptop.domain;

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
    private String priceDetailsGross;
    private String priceDetailsInfo;

    public Hotel(String name, String location, String latitude, String longitude,
                 String checkinFromTime, String checkinUntilTime, String checkoutFromTime,
                 String checkoutUntilTime, double reviewScore, String reviewScoreWord,
                 int reviewCount, String proposedAccommodation, String priceDetailsGross,
                 String priceDetailsInfo) {
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCheckinFromTime() {
        return checkinFromTime;
    }

    public void setCheckinFromTime(String checkinFromTime) {
        this.checkinFromTime = checkinFromTime;
    }

    public String getCheckinUntilTime() {
        return checkinUntilTime;
    }

    public void setCheckinUntilTime(String checkinUntilTime) {
        this.checkinUntilTime = checkinUntilTime;
    }

    public String getCheckoutFromTime() {
        return checkoutFromTime;
    }

    public void setCheckoutFromTime(String checkoutFromTime) {
        this.checkoutFromTime = checkoutFromTime;
    }

    public String getCheckoutUntilTime() {
        return checkoutUntilTime;
    }

    public void setCheckoutUntilTime(String checkoutUntilTime) {
        this.checkoutUntilTime = checkoutUntilTime;
    }

    public double getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(double reviewScore) {
        this.reviewScore = reviewScore;
    }

    public String getReviewScoreWord() {
        return reviewScoreWord;
    }

    public void setReviewScoreWord(String reviewScoreWord) {
        this.reviewScoreWord = reviewScoreWord;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getProposedAccommodation() {
        return proposedAccommodation;
    }

    public void setProposedAccommodation(String proposedAccommodation) {
        this.proposedAccommodation = proposedAccommodation;
    }

    public String getPriceDetailsGross() {
        return priceDetailsGross;
    }

    public void setPriceDetailsGross(String priceDetailsGross) {
        this.priceDetailsGross = priceDetailsGross;
    }

    public String getPriceDetailsInfo() {
        return priceDetailsInfo;
    }

    public void setPriceDetailsInfo(String priceDetailsInfo) {
        this.priceDetailsInfo = priceDetailsInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
