package com.prototype.triptop.identityprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public ResponseEntity<Void> login() throws AuthenticationServicesUnavailableException {
        authService.checkAPIStability();
        String authorizationURL = authService.getAuthorizationURL();
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).location(URI.create(authorizationURL)).build();
    }

    @GetMapping("/callback")
    public ResponseEntity<String> callback(String code, @RequestParam String state) throws InvalidRequestOriginException, NoAuthCodeException {
        String token = authService.handleAuthCallback(code, state);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestParam String accessToken) {
        if (!authService.verifyToken(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        String email = authService.fetchEmailFromToken(accessToken);
        System.out.println(authService.fetchUserIDFromToken(accessToken));
        Map<String, Object> userInfo = authService.getUserInfoFromRepository(email);
        return ResponseEntity.status(HttpStatus.OK).body(authService.changeObjectToUserInfo(userInfo));
    }

    // Add a POST mapping for logout.
}
