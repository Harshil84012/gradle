package com.sungjun.api.service;

import com.sungjun.requestDto.UserRequestDto;
import com.sungjun.responseDto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponseDto registerNewUser(UserRequestDto dto);

    Page<UserResponseDto> getAllUsers(String searchValue, Pageable pageable);
}
