package com.sungjun.requestDto;


import com.sungjun.entity.RoleEntity;
import com.sungjun.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleRequestDto {

    private UserEntity user;
    private RoleEntity role;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
