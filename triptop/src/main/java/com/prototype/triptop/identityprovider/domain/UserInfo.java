package com.prototype.triptop.identityprovider.domain;

public class UserInfo {
    private String firstName;
    private String lastName;
    private String email;
    private String prefix;

    public UserInfo() {
    }

    public UserInfo(String firstName, String lastName, String email, String prefix) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.prefix = prefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;

    }
}
