package com.prototype.triptop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prototype.triptop.strategy.UserInfoRefact;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleUserInfo implements UserInfoRefact {
    private String given_name;  // Voorname
    private String family_name; // Achtername
    private String email;

    public GoogleUserInfo() {
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public UserInfo toUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(given_name);
        userInfo.setLastName(family_name);
        userInfo.setPrefix(""); // Prefix wordt niet gegeven door Google
        userInfo.setEmail(email);
        return userInfo;
    }
}

