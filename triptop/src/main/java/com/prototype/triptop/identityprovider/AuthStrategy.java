package com.prototype.triptop.identityprovider;

import java.util.List;
import java.util.Map;

public interface AuthStrategy {
    String redirectURI = "http://localhost:8080/api/v1/auth/callback";

    String getAuthorizationURL();
    Authorizationtokens getTokens(String code);
    UserInfo getUserInfo(String accessToken);
    UserInfo changeObjectToUserInfo(Map<String, Object> userInfo);
}
