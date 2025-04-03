package com.prototype.triptop.strategy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prototype.triptop.TriptopPrototypeApplication;
import com.prototype.triptop.domain.Authorizationtokens;
import com.prototype.triptop.domain.GoogleUserInfo;
import com.prototype.triptop.domain.UserInfo;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.Map;

@Component
public class GoogleAuthStrategy implements AuthStrategy {

    private String clientId = TriptopPrototypeApplication.dotenv.get("GOOGLE_CLIENT_ID");

    private String clientSecret = TriptopPrototypeApplication.dotenv.get("GOOGLE_CLIENT_SECRET");

    private RestTemplate restTemplate = new RestTemplate();

    private String encodeURI(String URI) {
        try {
            return URLEncoder.encode(URI, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    private String encodedRedirectURI = encodeURI(AuthStrategy.redirectURI);

    @Override
    public String getAuthorizationURL() {
        return "https://accounts.google.com/o/oauth2/v2/auth?" +
                "client_id=" + clientId +
                "&response_type=code" +
                "&redirect_uri=" + encodedRedirectURI +
                "&scope=openid%20profile%20email" +
                "&state=google";
    }

    @Override
    public Authorizationtokens getTokens(String code) {

        String tokenURL = "https://oauth2.googleapis.com/token";

        String body = "client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&grant_type=authorization_code" +
                "&code=" + code +
                "&redirect_uri=" + encodedRedirectURI;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(tokenURL, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to get tokens from Google");
        }

        String responseBody = response.getBody();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(responseBody, Authorizationtokens.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserInfo getUserInfo(String accessToken) {
        String userInfoURL = "https://www.googleapis.com/oauth2/v3/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<GoogleUserInfo> response = restTemplate.exchange(
                userInfoURL,
                HttpMethod.GET,
                entity,
                GoogleUserInfo.class
        );


        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().toUserInfo();

        } else {
            System.out.println("Error: " + response.getStatusCode());
        }
        return null;
    }

    @Override
    public UserInfo changeObjectToUserInfo(Map<String, Object> userInfo) {
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setEmail((String) userInfo.get("EMAIL"));
        userInfo1.setFirstName((String) userInfo.get("VOORNAAM"));
        userInfo1.setLastName((String) userInfo.get("ACHTERNAAM"));
        userInfo1.setPrefix("");
        return userInfo1;
    }
}
