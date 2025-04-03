package com.prototype.triptop.identityprovider.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prototype.triptop.identityprovider.UserInfoRefact;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscordUserInfo implements UserInfoRefact {
    private String username;
    private String discriminator;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
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
        userInfo.setFirstName(username);
        userInfo.setLastName(discriminator);
        userInfo.setPrefix(""); // Prefix wordt niet gegeven door Discord
        userInfo.setEmail(email);
        return userInfo;
    }
}
