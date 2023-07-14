package com.sungjun.api.controller;


import com.sungjun.api.enums.ApiResponsesEnum;
import com.sungjun.api.service.LoginService;
import com.sungjun.requestDto.JwtRequestDto;
import com.sungjun.responseDto.ApiResponse;
import com.sungjun.responseDto.JwtResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authenticate")
public class LoginController {
    @Autowired
    private LoginService jwtService;


    @PostMapping({"/login"})
    public ResponseEntity<ApiResponse> createJwtToken(@RequestBody JwtRequestDto dto) throws Exception {
        JwtResponseDto jwtResponseDto = jwtService.createJwtToken(dto);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, ApiResponsesEnum.SIGN_IN_SUCCESSFULLY.getValue(), jwtResponseDto), HttpStatus.OK);
    }

}