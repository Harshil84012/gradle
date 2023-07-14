package com.sungjun.service;


import com.sungjun.requestDto.JwtRequestDto;
import com.sungjun.responseDto.JwtResponseDto;
import com.sungjun.responseDto.RefreshTokenResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    JwtResponseDto createJwtToken(JwtRequestDto dto);

    RefreshTokenResponse generateRefreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
