package com.sungjun.repository;

import com.sungjun.entity.PrivilegesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegesRepository extends JpaRepository<PrivilegesEntity, Integer> {
    Optional<PrivilegesEntity> findByPrivilegeNameIgnoreCase(String privilege);

    @Query(value = "select p from PrivilegesEntity p where lower(p.privilegeName) like lower(?1)")
    Page<PrivilegesEntity> getAll(String searchValue, Pageable pageable);
}
