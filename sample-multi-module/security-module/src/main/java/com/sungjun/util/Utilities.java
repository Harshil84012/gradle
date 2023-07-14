package com.sungjun.util;


import com.sungjun.entity.UserEntity;
import com.sungjun.repository.UserRepository;
import com.sungjun.responseDto.GetTokenClaims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class Utilities {
    @Autowired
    private UserRepository userRepository;

    @javax.annotation.Resource
    private GetTokenClaims getTokenClaims;

    public Date getDate() {
        return new Date();
    }

    public UserEntity currentUser() {
        UserEntity userEntity = userRepository.findById(getTokenClaims.getUserId()).get();
        System.out.println("current user in utilities  " + userEntity);
        return userRepository.findById(getTokenClaims.getUserId()).get();
    }

}
