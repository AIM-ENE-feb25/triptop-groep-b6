package com.prototype.triptop.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prototype.triptop.domain.Hotel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class HotelResponseParser {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Hotel> parseHotelsResponse(String jsonResponse) {
        List<Hotel> hotels = new ArrayList<>();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode resultNode = rootNode != null ? rootNode.get("result") : null;

            if (resultNode != null && resultNode.isArray()) {
                for (JsonNode hotelNode : resultNode) {
                    hotels.add(parseHotel(hotelNode));
                }
            }
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing JSON response: " + e.getMessage());
        }
        return hotels;
    }

    private Hotel parseHotel(JsonNode hotelNode) {
        String name = hotelNode.has("hotel_name") ? hotelNode.get("hotel_name").asText() : "Unknown";
        String location = hotelNode.has("city_name_en") ? hotelNode.get("city_name_en").asText() : "Unknown";
        String latitude = hotelNode.has("latitude") ? hotelNode.get("latitude").asText() : "Unknown";
        String longitude = hotelNode.has("longitude") ? hotelNode.get("longitude").asText() : "Unknown";

        String checkinFromTime = hotelNode.has("checkin") && hotelNode.get("checkin").has("from")
                ? hotelNode.get("checkin").get("from").asText() : "Unknown";
        String checkinUntilTime = hotelNode.has("checkin") && hotelNode.get("checkin").has("until")
                ? hotelNode.get("checkin").get("until").asText() : "Unknown";
        String checkoutFromTime = hotelNode.has("checkout") && hotelNode.get("checkout").has("from")
                ? hotelNode.get("checkout").get("from").asText() : "Unknown";
        String checkoutUntilTime = hotelNode.has("checkout") && hotelNode.get("checkout").has("until")
                ? hotelNode.get("checkout").get("until").asText() : "Unknown";

        double reviewScore = hotelNode.has("review_score") ? hotelNode.get("review_score").asDouble() : 0.0;
        String reviewScoreWord = hotelNode.has("review_score_word") ? hotelNode.get("review_score_word").asText() : "No Review";
        int reviewCount = hotelNode.has("review_nr") ? hotelNode.get("review_nr").asInt() : 0;

        String proposedAccommodation = hotelNode.has("unit_configuration_label") ? hotelNode.get("unit_configuration_label").asText() : "Unknown";
        BigDecimal priceDetailsGross = hotelNode.has("price_breakdown") && hotelNode.get("price_breakdown").has("gross_price")
                ? new BigDecimal(hotelNode.get("price_breakdown").get("gross_price").asText()) : BigDecimal.ZERO;
        BigDecimal priceDetailsInfo = hotelNode.has("price_breakdown") && hotelNode.get("price_breakdown").has("all_inclusive_price")
                ? new BigDecimal(hotelNode.get("price_breakdown").get("all_inclusive_price").asText()) : BigDecimal.ZERO;

        return new Hotel(
                name, location, latitude, longitude, checkinFromTime, checkinUntilTime, checkoutFromTime,
                checkoutUntilTime, reviewScore, reviewScoreWord, reviewCount, proposedAccommodation,
                priceDetailsGross, priceDetailsInfo);
    }
}
