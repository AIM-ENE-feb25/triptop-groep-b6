package com.prototype.triptop.identityprovider.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleUserInfo {
    private String given_name;  // First name
    private String family_name; // Last name
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

    public UserInfo toUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(given_name);
        userInfo.setLastName(family_name);
        userInfo.setPrefix("");
        userInfo.setEmail(email);
        return userInfo;
    }
}

