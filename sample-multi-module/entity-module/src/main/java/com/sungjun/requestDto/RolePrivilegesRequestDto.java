package com.sungjun.requestDto;


import com.sungjun.entity.PrivilegesEntity;
import com.sungjun.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RolePrivilegesRequestDto {

    private RoleEntity role;
    private PrivilegesEntity privilege;

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public PrivilegesEntity getPrivilege() {
        return privilege;
    }

    public void setPrivilege(PrivilegesEntity privilege) {
        this.privilege = privilege;
    }
}
