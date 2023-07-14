package com.sungjun.api.service;



import com.sungjun.requestDto.PrivilegesRequestDto;
import com.sungjun.responseDto.PrivilegesResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrivilegesService {
    PrivilegesResponseDto createPrivilege(PrivilegesRequestDto dto);

    Page<PrivilegesResponseDto> getAllPrivileges(String searchValue, Pageable pageable);
}
