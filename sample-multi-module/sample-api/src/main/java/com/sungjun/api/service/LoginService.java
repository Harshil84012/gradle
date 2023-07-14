package com.sungjun.api.service;



import com.sungjun.requestDto.JwtRequestDto;
import com.sungjun.responseDto.JwtResponseDto;

public interface LoginService {
    JwtResponseDto createJwtToken(JwtRequestDto dto);
}
