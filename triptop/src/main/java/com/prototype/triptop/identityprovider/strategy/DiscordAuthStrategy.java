package com.prototype.triptop.identityprovider.strategy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prototype.triptop.TriptopPrototypeApplication;
import com.prototype.triptop.identityprovider.domain.Authorizationtokens;
import com.prototype.triptop.identityprovider.domain.DiscordUserInfo;
import com.prototype.triptop.identityprovider.domain.UserInfo;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.Map;

@Component
public class DiscordAuthStrategy implements AuthStrategy {

    private String clientId = TriptopPrototypeApplication.dotenv.get("DISCORD_CLIENT_ID");

    private String clientSecret = TriptopPrototypeApplication.dotenv.get("DISCORD_CLIENT_SECRET");

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
        System.out.println("Discord Client ID: " + clientId);
        return "https://discord.com/api/oauth2/authorize?client_id=" + clientId
                + "&response_type=code&redirect_uri=" + encodedRedirectURI
                + "&scope=identify%20email&prompt=consent&state=discord";
    }

    @Override
    public Authorizationtokens getTokens(String code) {

        String tokenURL = "https://discord.com/api/oauth2/token";

        String body = "client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&grant_type=authorization_code" +
                "&code=" + code +
                "&redirect_uri=" + encodedRedirectURI +
                "&scope=identify%20email";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(tokenURL, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to get tokens from Discord");
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
        String userInfoURL = "https://discord.com/api/users/@me";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<DiscordUserInfo> response = restTemplate.exchange(
                userInfoURL,
                HttpMethod.GET,
                entity,
                DiscordUserInfo.class
        );

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to get user info from Discord");
        }
        return response.getBody().toUserInfo();
    }

    @Override
    public UserInfo changeObjectToUserInfo(Map<String, Object> userInfo) {
        UserInfo user = new UserInfo();
        user.setEmail((String) userInfo.get("EMAIL"));
        user.setFirstName((String) userInfo.get("VOORNAAM"));
        user.setLastName((String) userInfo.get("ACHTERNAAM"));
        user.setPrefix("");
        return user;
    }
}
