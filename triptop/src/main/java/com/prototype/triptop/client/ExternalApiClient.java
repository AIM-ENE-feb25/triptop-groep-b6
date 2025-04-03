package com.prototype.triptop.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class ExternalApiClient {
    private final RestTemplate restTemplate;

    public ExternalApiClient(RestTemplate restTemplate) {
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
}
