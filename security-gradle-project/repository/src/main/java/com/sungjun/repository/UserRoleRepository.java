package com.sungjun.repository;

import com.sungjun.entity.UserEntity;
import com.sungjun.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    Optional<UserRoleEntity> findByUser(UserEntity userEntity);



}
