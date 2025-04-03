package com.prototype.triptop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prototype.triptop.domain.Hotel;
import com.prototype.triptop.domain.HotelSearchRequest;
import com.prototype.triptop.domain.HotelSearchResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callApi(String endpoint, Map<String, String> params, String apiKey) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(java.net.URI.create(endpoint));
        params.forEach(builder::queryParam);

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", "booking-com.p.rapidapi.com");
        headers.set("x-rapidapi-key", apiKey);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);

        return response.getBody();
    }

    public HotelSearchResponse searchHotels(HotelSearchRequest request, String apiKey) {
        String endpoint = "https://booking-com.p.rapidapi.com/v1/hotels/search";
        Map<String, String> params = new HashMap<>();
        params.put("locale", "en-us");
        params.put("dest_type", "city");
        params.put("filter_by_currency", "EUR");
        params.put("order_by", "popularity");
        params.put("units", "metric");
        params.put("dest_id", request.getDestinationId());
        params.put("checkin_date", request.getCheckInDate());
        params.put("checkout_date", request.getCheckOutDate());
        params.put("adults_number", String.valueOf(request.getAdults()));
        params.put("children_number", String.valueOf(request.getChildren()));
        params.put("room_number", String.valueOf(request.getRooms()));

        String response = callApi(endpoint, params, apiKey);
        List<Hotel> hotels = parseHotelsResponse(response);

        return new HotelSearchResponse(hotels);
    }

    private List<Hotel> parseHotelsResponse(String jsonResponse) {
        List<Hotel> hotels = new ArrayList<>();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode resultNode = rootNode != null ? rootNode.get("result") : null;

            if (resultNode != null && resultNode.isArray()) {
                for (JsonNode hotelNode : resultNode) {
                    String name = hotelNode.has("hotel_name") ? hotelNode.get("hotel_name").asText() : "Unknown";
                    String location = hotelNode.has("city_name_en") ? hotelNode.get("city_name_en").asText() : "Unknown";
                    String latitude = hotelNode.has("latitude") ? hotelNode.get("latitude").asText() : "Unknown";
                    String longitude = hotelNode.has("longitude") ? hotelNode.get("longitude").asText() : "Unknown";

                    String checkinFromTime = hotelNode.has("checkin") && hotelNode.get("checkin").has("from")
                            ? hotelNode.get("checkin").get("from").asText() : "Unknown";
                    String checkinUntilTime = hotelNode.has("checkin") && hotelNode.get("checkin").has("until") && !hotelNode.get("checkin").get("until").asText().isEmpty()
                            ? hotelNode.get("checkin").get("until").asText() : "Unknown";
                    String checkoutFromTime = hotelNode.has("checkout") && hotelNode.get("checkout").has("from") && !hotelNode.get("checkout").get("from").asText().isEmpty()
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

                    hotels.add(new Hotel(
                            name, location, latitude, longitude, checkinFromTime, checkinUntilTime, checkoutFromTime,
                            checkoutUntilTime, reviewScore, reviewScoreWord, reviewCount, proposedAccommodation,
                            priceDetailsGross, priceDetailsInfo));
                }
            }
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing JSON response: " + e.getMessage());
        }
        return hotels;
    }
}