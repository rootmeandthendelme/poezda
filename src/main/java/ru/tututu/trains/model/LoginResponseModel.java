package ru.tututu.trains.model;

public class LoginResponseModel {
    private String accessToken;

    public LoginResponseModel(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
