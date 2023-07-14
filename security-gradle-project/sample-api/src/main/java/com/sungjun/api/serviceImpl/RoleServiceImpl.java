package com.sungjun.api.serviceImpl;




import com.sungjun.api.service.RoleService;
import com.sungjun.entity.PrivilegesEntity;
import com.sungjun.entity.RoleEntity;
import com.sungjun.entity.RolePrivilegesEntity;
import com.sungjun.enums.ExceptionEnum;
import com.sungjun.exception.CustomException;
import com.sungjun.repository.PrivilegesRepository;
import com.sungjun.repository.RolePrivilegesRepository;
import com.sungjun.repository.RoleRepository;
import com.sungjun.requestDto.RoleRequestDto;
import com.sungjun.responseDto.RoleResponseDto;

import com.sungjun.util.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RolePrivilegesRepository rolePrivilegesRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PrivilegesRepository privilegeRepository;
    @Autowired
    private Utilities utilities;

    @Override
    public RoleResponseDto createRole(RoleRequestDto dto) {

        roleRepository.findByRoleNameIgnoreCase(dto.getRoleName()).ifPresent(x -> {
            throw new CustomException(ExceptionEnum.ROLE_ALREADY_EXISTS.getValue(), HttpStatus.CONFLICT);
        });

        RoleEntity role = new RoleEntity();
        role.setRoleName(dto.getRoleName());
        role.setCreatedBy(utilities.currentUser());
        role.setUpdatedBy(utilities.currentUser());
        role.setStatus(Boolean.TRUE);

        roleRepository.save(role);

        Set<PrivilegesEntity> privileges = new HashSet<>();
        dto.getPrivilege().forEach(privilage -> {
            PrivilegesEntity byPrivilegeName = privilegeRepository.findByPrivilegeNameIgnoreCase(privilage).orElse(new PrivilegesEntity());
            if (byPrivilegeName.getPrivilegeName() == null || byPrivilegeName.getPrivilegeName().isEmpty()) {
                PrivilegesEntity privilege1 = new PrivilegesEntity();
                privilege1.setPrivilegeName(privilage);
                privilege1.setCreatedBy(utilities.currentUser());
                privilege1.setUpdatedBy(utilities.currentUser());
                privilege1.setStatus(Boolean.TRUE);
                PrivilegesEntity save = this.privilegeRepository.save(privilege1);
                privileges.add(save);
            } else {
                privileges.add(byPrivilegeName);
            }
        });
        privileges.forEach(privilege -> {
            RolePrivilegesEntity privilegeRole = new RolePrivilegesEntity();
            privilegeRole.setRole(role);
            privilegeRole.setPrivilege(privilege);
            rolePrivilegesRepository.save(privilegeRole);
        });
        return mapToRoleResponseDto(role, privileges);
    }

    @Override
    public Page<RoleResponseDto> getRole(String searchValue, Pageable pageable) {
        return roleRepository.findRoleWithPagination("%" + searchValue + "%", pageable).map(this::mapToRoleResponseDto);
    }

    private RoleResponseDto mapToRoleResponseDto(RoleEntity role, Set<PrivilegesEntity> privilege) {
        return Optional.ofNullable(role).map(e -> {
            var result = this.modelMapper.map(e, RoleResponseDto.class);
            result.setPrivilege(privilege.stream().map(p -> p.getPrivilegeName()).collect(Collectors.toSet()));
            return result;
        }).orElseGet(RoleResponseDto::new);
    }

    private RoleResponseDto mapToRoleResponseDto(RoleEntity role) {
        return Optional.ofNullable(role).map(e -> {
            var privilegeRoleMapping = rolePrivilegesRepository.findByRole(role);
            var result = this.modelMapper.map(e, RoleResponseDto.class);
            result.setPrivilege(privilegeRoleMapping.stream().map(p -> p.getPrivilege().getPrivilegeName()).collect(Collectors.toSet()));
            return result;
        }).orElseGet(RoleResponseDto::new);
    }

}
