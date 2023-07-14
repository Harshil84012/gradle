package com.sungjun.api.service;


import com.sungjun.requestDto.RoleRequestDto;
import com.sungjun.responseDto.RoleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    RoleResponseDto createRole(RoleRequestDto roleRequestDto);

    Page<RoleResponseDto> getRole(String searchValue, Pageable pageable);
}
