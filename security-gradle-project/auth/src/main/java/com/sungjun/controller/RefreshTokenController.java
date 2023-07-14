package com.sungjun.controller;

import com.sungjun.responseDto.RefreshTokenResponse;
import com.sungjun.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/refreshToken")
public class RefreshTokenController {

    @Autowired
    private LoginService jwtService;

    @GetMapping
    public RefreshTokenResponse refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return jwtService.generateRefreshToken(httpServletRequest, httpServletResponse);
    }
}
