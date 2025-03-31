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

import java.util.Map;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callApi(String endpoint, Map<String, String> params, String apiKey) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(java.net.URI.create(endpoint));
        params.forEach((key, value) -> builder.queryParam(key, value));

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", "booking-com15.p.rapidapi.com");
        headers.set("x-rapidapi-key", apiKey);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class);
        return response.getBody();
    }

    public HotelSearchResponse searchHotels(HotelSearchRequest request, String apiKey) {
        String endpoint = "https://booking-com15.p.rapidapi.com/v1/hotels/search";
        Map<String, String> params = Map.of(
            "destination_id", request.getDestinationId(),
            "adults_number", String.valueOf(request.getAdults()),
            "children_number", String.valueOf(request.getChildren()),
            "room_number", String.valueOf(request.getRooms())
        );

        String response = callApi(endpoint, params, apiKey);
        // Parse the response into HotelSearchResponse (parsing logic depends on API response format)
        return new HotelSearchResponse(/* parsed hotels */);
    }
}