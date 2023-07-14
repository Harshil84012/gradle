package com.sungjun.api.serviceImpl;


import com.sungjun.api.service.PrivilegesService;
import com.sungjun.entity.PrivilegesEntity;
import com.sungjun.enums.ExceptionEnum;
import com.sungjun.exception.CustomException;
import com.sungjun.repository.PrivilegesRepository;
import com.sungjun.requestDto.PrivilegesRequestDto;
import com.sungjun.responseDto.PrivilegesResponseDto;
import com.sungjun.util.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrivilegesServiceImpl implements PrivilegesService {
    @Autowired
    private PrivilegesRepository privilegesRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Utilities utilities;


    @Override
    public PrivilegesResponseDto createPrivilege(PrivilegesRequestDto dto) {
        privilegesRepository.findByPrivilegeNameIgnoreCase(dto.getPrivilegeName()).ifPresent(x -> {
            throw new CustomException(ExceptionEnum.PRIVILEGE_ALREADY_EXISTS.getValue(), HttpStatus.CONFLICT);
        });
        PrivilegesEntity privilege = new PrivilegesEntity();
        privilege.setPrivilegeName(dto.getPrivilegeName());
        privilege.setCreatedAt(utilities.getDate());
        privilege.setUpdatedAt(utilities.getDate());
        privilege.setStatus(Boolean.TRUE);
        privilege.setCreatedBy(utilities.currentUser());
        privilege.setUpdatedBy(utilities.currentUser());

        privilegesRepository.save(privilege);
        return mapToUserResponseDto(privilege);
    }

    @Override
    public Page<PrivilegesResponseDto> getAllPrivileges(String searchValue, Pageable pageable) {
        return privilegesRepository.getAll("%" + searchValue + "%", pageable).map(this::mapToUserResponseDto);
    }

//    @Override
//    public List<PrivilegesResponseDto> getAllPrivileges() {
//        return privilegesRepository.findAll().stream().map(p->mapToUserResponseDto(p)).collect(Collectors.toList());
//    }

    private PrivilegesResponseDto mapToUserResponseDto(PrivilegesEntity privilege) {
        return Optional.ofNullable(privilege).map(e -> {
            var result = this.modelMapper.map(e, PrivilegesResponseDto.class);
            return result;
        }).orElseGet(PrivilegesResponseDto::new);
    }


}
