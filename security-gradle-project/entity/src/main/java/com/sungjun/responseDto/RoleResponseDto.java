package com.sungjun.responseDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;


@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseDto {

    private String roleName;
    private Set<String> privilege;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<String> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Set<String> privilege) {
        this.privilege = privilege;
    }
}
