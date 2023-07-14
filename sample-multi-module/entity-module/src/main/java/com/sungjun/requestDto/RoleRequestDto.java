package com.sungjun.requestDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestDto {

    //    @NotEmpty(message = "role name must not be empty")
    private String roleName;

    //    @NotEmpty(message = "privileges must not be empty")
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
