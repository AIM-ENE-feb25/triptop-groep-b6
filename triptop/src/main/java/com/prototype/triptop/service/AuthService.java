package com.prototype.triptop.service;

import com.prototype.triptop.repository.AuthRepository;
import com.prototype.triptop.misc.ExternalAPIAvailabilityCheck;
import com.prototype.triptop.domain.Authorizationtokens;
import com.prototype.triptop.domain.UserInfo;
import com.prototype.triptop.exception.AuthenticationServicesUnavailableException;
import com.prototype.triptop.exception.InvalidRequestOriginException;
import com.prototype.triptop.exception.NoAuthCodeException;
import com.prototype.triptop.strategy.AuthStrategy;
import com.prototype.triptop.strategy.DiscordAuthStrategy;
import com.prototype.triptop.strategy.GoogleAuthStrategy;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private AuthStrategy authStrategy;
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private ExternalAPIAvailabilityCheck availabilityService;

    private static final String SECRET_KEY = "1345364765432sdnbunslebnesdasbg4nw903nf092g4nbn09wasn093nw09g3w90bn09wn390n";

    private Map<String, UserInfo> userTokens = new HashMap<>();

    public AuthService() {
        this.availabilityService = new ExternalAPIAvailabilityCheck(new RestTemplate());
        try {
            checkAPIStability();
        } catch (Exception e) {
            System.out.println("[System]: Authentication services are down");
            System.out.println("[System]: System will shut down.");
            System.exit(0);
        }
    }

    public String getAuthorizationURL() {
        return authStrategy.getAuthorizationURL();
    }

    public Authorizationtokens getTokens(String code) {
        return authStrategy.getTokens(code);
    }

    public UserInfo getUserInfo(String accessToken) {
        return authStrategy.getUserInfo(accessToken);
    }

    public Map<String, Object> getUserInfoFromRepository(String email) {
        return authRepository.getUser(email);
    }

    public void setAuthorizationStrategy(AuthStrategy authStrategy) {
        this.authStrategy = authStrategy;
    }

    public void checkRequestsOrigin(String origin) throws InvalidRequestOriginException {
        if (origin.equals("google")) {
            setAuthorizationStrategy(new GoogleAuthStrategy());
        } else if (origin.equals("discord")) {
            setAuthorizationStrategy(new DiscordAuthStrategy());
        } else {
            throw new InvalidRequestOriginException("Invalid request origin");
        }
    }

    public void checkAPIStability() throws AuthenticationServicesUnavailableException {
        boolean isGoogleAvailable = availabilityService.isGoogleAvailable();
        boolean isDiscordAvailable = availabilityService.isDiscordAvailable();

        if (!isGoogleAvailable && !isDiscordAvailable) {
            throw new AuthenticationServicesUnavailableException("Both Authentication services down. Come back another time");
        } else if (!isGoogleAvailable) {
            setAuthorizationStrategy(new DiscordAuthStrategy());
        } else if (!isDiscordAvailable) {
            setAuthorizationStrategy(new GoogleAuthStrategy());
        } else {
            setAuthorizationStrategy(new GoogleAuthStrategy());
        }
    }

    public boolean userExists(String email) {
        return authRepository.userExists(email);
    }

    public void updateUser(UserInfo userInfo, String accessToken) {
        authRepository.updateUser(userInfo, accessToken);
    }


    public void saveUser(UserInfo userInfo, String accessToken) {
        authRepository.saveUser(userInfo, accessToken);
    }

    public String handleAuthCallback(String code, String state) throws NoAuthCodeException, InvalidRequestOriginException {
        if (code.isEmpty() || code.isBlank()) {
            throw new NoAuthCodeException("No auth code provided");
        }

        checkRequestsOrigin(state);
        String accessToken = getTokens(code).getAccess_token();
        UserInfo userInfo = getUserInfo(accessToken);

        if (userExists(userInfo.getEmail())) {
            updateUser(userInfo, accessToken);
        } else {
            saveUser(userInfo, accessToken);
        }

        return createJWTToken(userInfo);
    }

    private String createJWTToken(UserInfo userInfo) {


        String token = Jwts.builder()
                .setSubject(userInfo.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        userTokens.put(token, userInfo);

        return token;
    }

    public boolean verifyToken(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return userTokens.containsKey(token);
        } catch (Exception e) {
            return false;
        }
    }

    public String fetchEmailFromToken(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }
        UserInfo userInfo = userTokens.get(token);
        return userInfo != null ? userInfo.getEmail() : null;
    }

    public int fetchUserIDFromToken(String token) {
        if (token == null || token.isEmpty()) {
            return -1;
        }
        UserInfo userInfo = userTokens.get(token);
        return (int) authRepository.getUser(userInfo.getEmail()).get("gebruikersId");
    }

    public UserInfo changeObjectToUserInfo(Map<String, Object> userInfo) {
        return authStrategy.changeObjectToUserInfo(userInfo);
    }
}
