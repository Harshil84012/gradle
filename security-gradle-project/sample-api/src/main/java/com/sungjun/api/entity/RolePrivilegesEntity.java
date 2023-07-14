//package com.sungjun.api.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.sungjun.api.entity.PrivilegesEntity;
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "role_privilege_mapping_tbl")
//
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//public class RolePrivilegesEntity {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "role_id", referencedColumnName = "id")
//    private RoleEntity role;
//
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "privilege_id", referencedColumnName = "id")
//    private PrivilegesEntity privilege;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public RoleEntity getRole() {
//        return role;
//    }
//
//    public void setRole(RoleEntity role) {
//        this.role = role;
//    }
//
//    public PrivilegesEntity getPrivilege() {
//        return privilege;
//    }
//
//    public void setPrivilege(PrivilegesEntity privilege) {
//        this.privilege = privilege;
//    }
//}
