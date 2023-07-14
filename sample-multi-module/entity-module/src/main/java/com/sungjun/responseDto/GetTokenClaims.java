package com.sungjun.responseDto;


public class GetTokenClaims {
    private String token;
    private String username;
    private Integer userId;

    public GetTokenClaims(String token, String username, Integer userId, String role) {
        this.token = token;
        this.username = username;
        this.userId = userId;
        this.role = role;
    }

    public GetTokenClaims() {

    }

    private String role;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
