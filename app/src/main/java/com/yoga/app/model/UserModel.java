package com.yoga.app.model;

public class UserModel {
    private String userName, email, password, userId, profile;

    public UserModel() {
    }

    public UserModel(String userName, String email, String password, String userId, String profile) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.profile = profile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
