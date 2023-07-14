package com.sungjun.responseDto;


public class JwtResponseDto {

    private String token;

    public JwtResponseDto(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }


    public JwtResponseDto() {

    }

    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public JwtResponseDto(String token) {
        this.token = token;
    }
}
