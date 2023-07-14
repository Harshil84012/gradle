package com.sungjun.api.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sungjun.api.enums.ExceptionEnum;
import com.sungjun.api.service.LoginService;
import com.sungjun.entity.PrivilegesEntity;
import com.sungjun.entity.RolePrivilegesEntity;
import com.sungjun.entity.UserEntity;
import com.sungjun.entity.UserRoleEntity;
import com.sungjun.exception.CustomException;
import com.sungjun.repository.RolePrivilegesRepository;
import com.sungjun.repository.UserRepository;
import com.sungjun.repository.UserRoleRepository;
import com.sungjun.requestDto.JwtRequestDto;
import com.sungjun.responseDto.JwtResponseDto;
import com.sungjun.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RolePrivilegesRepository rolePrivilegesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public JwtResponseDto createJwtToken(JwtRequestDto getTokenRequestDto) {

        Optional<UserEntity> userEntity = userRepository.findByUsernameIgnoreCase(getTokenRequestDto.getUsername());
        System.out.println("getTokenRequestDto.getUsername() = " + getTokenRequestDto.getUsername());
        var user = userRepository.findByUsernameIgnoreCase(getTokenRequestDto.getUsername())
                .orElseThrow(() -> new CustomException(ExceptionEnum.USERNAME_NOT_FOUND.getValue(), HttpStatus.BAD_REQUEST));

        if (!passwordEncoder.matches(getTokenRequestDto.getPassword(), user.getPassword())) {
            throw new CustomException(ExceptionEnum.INCORRECT_PASSWORD.getValue(), HttpStatus.NOT_FOUND);
        }
        return getTokenResponse(userEntity.get());
    }

    private JwtResponseDto getTokenResponse(UserEntity userEntity) {

        String role = null;
        Optional<UserRoleEntity> systemUserRoleMappingEntity = userRoleRepository.findByUser(userEntity);

        role = systemUserRoleMappingEntity.get().getRole().getRoleName();

        System.out.println("systemUserRoleMappingEntity systemRole ::::::::::= " + role);
        System.out.println("systemUserRoleMappingEntity :::::: = " + systemUserRoleMappingEntity.get().toString());

        Optional<RolePrivilegesEntity> roleRightsMappingEntity = rolePrivilegesRepository.findById(systemUserRoleMappingEntity.get().getId());


        System.out.println("roleRightsMappingEntity :::::= " + roleRightsMappingEntity.toString());
        List<PrivilegesEntity> rightsEntitiesList = roleRightsMappingEntity.stream().map(RolePrivilegesEntity::getPrivilege).collect(Collectors.toList());

        try {
            return new JwtResponseDto(jwtTokenProvider.createToken(userEntity.getUsername(), userEntity.getId(), role, rightsEntitiesList));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
