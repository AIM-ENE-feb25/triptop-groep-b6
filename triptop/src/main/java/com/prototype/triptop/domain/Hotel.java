package com.prototype.triptop.domain;

import java.math.BigDecimal;

public record Hotel(
        String name,
        String location,
        String latitude,
        String longitude,
        String checkinFromTime,
        String checkinUntilTime,
        String checkoutFromTime,
        String checkoutUntilTime,
        double reviewScore,
        String reviewScoreWord,
        int reviewCount,
        String proposedAccommodation,
        BigDecimal priceDetailsGross,
        BigDecimal priceDetailsInfo
) {
}
