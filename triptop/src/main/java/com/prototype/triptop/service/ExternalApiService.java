package com.prototype.triptop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prototype.triptop.domain.City;
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

        System.out.println("API Response: " + response.getBody());

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
                    String priceDetailsGross = hotelNode.has("price_breakdown") && hotelNode.get("price_breakdown").has("gross_price")
                            ? hotelNode.get("price_breakdown").get("gross_price").asText() : "Unknown";
                    String priceDetailsInfo = hotelNode.has("price_breakdown") && hotelNode.get("price_breakdown").has("all_inclusive_price")
                            ? hotelNode.get("price_breakdown").get("all_inclusive_price").asText() : "Unknown";

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


//    public List<City> getCities(String cityName, String apiKey) {
//        String response = callCitiesApi(cityName, null, apiKey);
//        return parseCitiesResponse(response);
//    }

//    public List<City> parseCitiesResponse(String jsonResponse) {
//        List<City> cities = new ArrayList<>();
//        try {
//            JsonNode rootNode = objectMapper.readTree(jsonResponse);
//            JsonNode resultNode = rootNode != null ? rootNode.get("result") : null;
//
//            if (resultNode != null && resultNode.isArray()) {
//                for (JsonNode cityNode : resultNode) {
//                    // Debugging: print JSON output
//                    System.out.println("Processing city: " + cityNode);
//
//                    if (!cityNode.has("country") || !cityNode.get("country").asText().equalsIgnoreCase("nl")) {
//                        continue;
//                    }
//
//                    // Controleer of city_id aanwezig en niet null is
//                    if (!cityNode.has("city_id") || cityNode.get("city_id").isNull()) {
//                        System.err.println("Skipping city due to missing city_id: " + cityNode);
//                        continue;
//                    }
//
//                    Long cityId = cityNode.get("city_id").asLong(); // <- Controleer of dit altijd werkt
//                    String cityName = cityNode.has("name") ? cityNode.get("name").asText() : "Unknown";
//                    String country = cityNode.has("country") ? cityNode.get("country").asText() : "Unknown";
//                    String latitude = cityNode.has("latitude") ? cityNode.get("latitude").asText() : "0.0";
//                    String longitude = cityNode.has("longitude") ? cityNode.get("longitude").asText() : "0.0";
//                    String timezone = cityNode.has("timezone_name") ? cityNode.get("timezone_name").asText() : "Unknown";
//
//                    City city = new City(cityId, cityName, country, latitude, longitude, timezone);
//                    cities.add(city);
//                }
//            }
//        } catch (JsonProcessingException e) {
//            System.err.println("Error parsing JSON response: " + e.getMessage());
//        }
//
//        return cities;
//    }

//
//    public String callCitiesApi(String cityName, String country, String apiKey) {
//        String endpoint = "https://booking-com.p.rapidapi.com/v1/static/cities";
//        Map<String, String> params = new HashMap<>();
//        params.put("page", "0");
//        params.put("country", "nl");
//        params.put("city", cityName);
//
//        return callApi(endpoint, params, apiKey);
//    }
//
//    public List<City> getCities(Long city_id, String cityName, String country, String longitude, String latitude, String rapidApiKey) {
//        String url = "https://booking-com.p.rapidapi.com/v1/static/cities";
//
//        UriComponentsBuilder uribuilder = UriComponentsBuilder.fromHttpUrl(url);
//
//        if (country != null) {
//            uribuilder.queryParam("country", country);
//        }
//        if (cityName != null) {
//            uribuilder.queryParam("city", cityName);
//        }
//        if (longitude != null) {
//            uribuilder.queryParam("longitude", longitude);
//        }
//        if (latitude != null) {
//            uribuilder.queryParam("latitude", latitude);
//        }
//
//        uribuilder.queryParam("page", "0");
//
//        String responseURL = uribuilder.toUriString();
//
//        System.out.println("Request URL: " + responseURL);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-RapidAPI-Key", rapidApiKey);
//        headers.set("X-RapidAPI-Host", "booking-com.p.rapidapi.com");
//
//        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(responseURL, HttpMethod.GET, requestEntity, String.class);
//
//        System.out.println(response.getBody());
//
//        return parseCitiesResponse(response.getBody());
//    }
}