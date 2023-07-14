package com.sungjun.controller;


import com.sungjun.enums.ApiResponsesEnum;
import com.sungjun.requestDto.JwtRequestDto;
import com.sungjun.responseDto.ApiResponse;
import com.sungjun.responseDto.JwtResponseDto;
import com.sungjun.service.LoginService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/authenticate")
public class LoginController {

    @Autowired
    private LoginService jwtService;

//    private static final Logger LOG = Logger.getLogger(InterceptorMain.class.getName());

    @PostMapping({"/login"})
    public ResponseEntity<ApiResponse> createJwtToken(@RequestBody JwtRequestDto dto) throws Exception {
//        LOG.log(Level.INFO, "Index API is calling");
        JwtResponseDto jwtResponseDto = jwtService.createJwtToken(dto);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, ApiResponsesEnum.SIGN_IN_SUCCESSFULLY.getValue(), jwtResponseDto), HttpStatus.OK);
    }


}