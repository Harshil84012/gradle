package com.sungjun.responseDto;

public class RefreshTokenResponse {


    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public RefreshTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }


    public RefreshTokenResponse() {

    }
}
