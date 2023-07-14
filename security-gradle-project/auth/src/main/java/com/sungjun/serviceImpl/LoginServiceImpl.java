package com.sungjun.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sungjun.entity.PrivilegesEntity;
import com.sungjun.entity.RolePrivilegesEntity;
import com.sungjun.entity.UserEntity;
import com.sungjun.entity.UserRoleEntity;

import com.sungjun.enums.ExceptionEnum;
import com.sungjun.exception.CustomException;
import com.sungjun.jwt.JwtTokenProvider;
import com.sungjun.repository.RolePrivilegesRepository;
import com.sungjun.repository.UserRepository;
import com.sungjun.repository.UserRoleRepository;
import com.sungjun.requestDto.JwtRequestDto;
import com.sungjun.responseDto.JwtResponseDto;
import com.sungjun.responseDto.RefreshTokenResponse;
import com.sungjun.service.LoginService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        System.out.println("user :::::::::: " + user);
        if (!passwordEncoder.matches(getTokenRequestDto.getPassword(), user.getPassword())) {
            throw new CustomException(ExceptionEnum.INCORRECT_PASSWORD.getValue(), HttpStatus.NOT_FOUND);
        }
        return getTokenResponse(userEntity.get());

    }


    @Override
    public RefreshTokenResponse generateRefreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        System.out.println(" ::::::::::::::: generateRefreshToken::::::::::::::::::::::");
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        System.out.println("token = " + token);

        if ((token != null && !token.isEmpty())) {
            try {
                jwtTokenProvider.validateToken(token);
            } catch (JwtException | IllegalArgumentException e) {
                System.out.println("jwt exception catch block inside ::::::: ");
            }
        }
        return jwtTokenProvider.createAccessTokenFromRefreshToken(token);
    }

    private JwtResponseDto getTokenResponse(UserEntity userEntity) {

        String role = null;
        Optional<UserRoleEntity> systemUserRoleMappingEntity = userRoleRepository.findByUser(userEntity);

        role = systemUserRoleMappingEntity.get().getRole().getRoleName();

        System.out.println("systemUserRoleMappingEntity systemRole ::::::::::= " + role);
        System.out.println("systemUserRoleMappingEntity :::::: = " + systemUserRoleMappingEntity.get());

        Optional<RolePrivilegesEntity> roleRightsMappingEntity = rolePrivilegesRepository.findById(systemUserRoleMappingEntity.get().getId());


        System.out.println("roleRightsMappingEntity :::::= " + roleRightsMappingEntity.toString());
        List<PrivilegesEntity> rightsEntitiesList = roleRightsMappingEntity.stream().map(RolePrivilegesEntity::getPrivilege).collect(Collectors.toList());

        try {
            return jwtTokenProvider.createAccessToken(userEntity.getUsername(), userEntity.getId(), role, rightsEntitiesList);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
