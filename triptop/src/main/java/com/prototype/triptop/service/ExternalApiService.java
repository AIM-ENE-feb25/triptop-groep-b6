// ExternalApiService.java
package com.prototype.triptop.service;

import com.prototype.triptop.domain.HotelSearchRequest;
import com.prototype.triptop.domain.HotelSearchResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;

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
        // Parse the response into HotelSearchResponse (parsing logic depends on API response format)
        return new HotelSearchResponse(/* parsed hotels */);
    }
}