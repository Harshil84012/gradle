package com.sungjun.api.serviceImpl;

import com.sungjun.api.enums.ExceptionEnum;
import com.sungjun.api.service.UserService;
import com.sungjun.entity.RoleEntity;
import com.sungjun.entity.UserEntity;
import com.sungjun.entity.UserRoleEntity;
import com.sungjun.exception.CustomException;
import com.sungjun.repository.RoleRepository;
import com.sungjun.repository.UserRepository;
import com.sungjun.repository.UserRoleRepository;
import com.sungjun.requestDto.UserRequestDto;
import com.sungjun.responseDto.UserResponseDto;
import com.sungjun.util.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Utilities utilities;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto registerNewUser(UserRequestDto dto) {


        var userEntity1 = userRepository.findByUsernameIgnoreCase(dto.getUsername());
        if (userEntity1.isPresent()) {
            throw new CustomException(ExceptionEnum.USER_ALREADY_EXISTS.getValue(), HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setCreatedAt(utilities.getDate());
        userEntity.setUpdatedAt(utilities.getDate());
        userEntity.setStatus(Boolean.TRUE);


        //save user
        userEntity.setCreatedBy(userEntity);
        userEntity.setUpdatedBy(userEntity);
        userRepository.save(userEntity);

        //UserRole Mapping
        RoleEntity role = roleRepository.findByRoleNameIgnoreCase(dto.getRole()).orElseThrow(() -> new CustomException(ExceptionEnum.ROLE_NOT_FOUND.getValue(), HttpStatus.NOT_FOUND));

        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setRole(role);
        userRole.setUser(userEntity);
        userRole.setCreatedBy(utilities.currentUser());
        userRole.setUpdatedBy(utilities.currentUser());
        userRole.setStatus(Boolean.TRUE);
        userRoleRepository.save(userRole);

        //update userEntity
        return mapToUserResponseDto(userEntity);

    }

    @Override
    public Page<UserResponseDto> getAllUsers(String searchValue, Pageable pageable) {
        return userRepository.getAll("%" + searchValue + "%", pageable).map(this::mapToUserResponseDto);
    }

    private UserResponseDto mapToUserResponseDto(UserEntity user) {
        return Optional.ofNullable(user).map(e -> {
            var userRoleMapping = userRoleRepository.findByUser(user);
            var result = this.modelMapper.map(e, UserResponseDto.class);
            Optional.ofNullable(userRoleMapping).ifPresent(u -> result.setRole(u.get().getRole().getRoleName()));
            return result;
        }).orElseGet(UserResponseDto::new);
    }


}
