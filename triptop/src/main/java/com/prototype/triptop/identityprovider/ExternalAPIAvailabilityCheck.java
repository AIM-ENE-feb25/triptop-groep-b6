package com.prototype.triptop.identityprovider;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalAPIAvailabilityCheck {
    private final RestTemplate restTemplate;

    private static final String GOOGLE_JWK_SET_URI = "https://www.googleapis.com/oauth2/v3/certs";
    private static final String DISCORD_JWK_SET_URI = "https://discord.com/api/oauth2/keys";

    public ExternalAPIAvailabilityCheck(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isGoogleAvailable() {
        return isAPIAvailable(GOOGLE_JWK_SET_URI);
    }

    public boolean isDiscordAvailable() {
        return isAPIAvailable(DISCORD_JWK_SET_URI);
    }

    private boolean isAPIAvailable(String uri) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
////            ALLEEN VOOR TESTEN
//            if (uri.contains("discord")) {
//                return false;
//            }
//            else
                if (uri.contains("google")) {
                return false;
            }
            System.out.println("API: " + uri + " is up and running");
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + uri + " another API will be used");
            return false;
        }
    }
}
