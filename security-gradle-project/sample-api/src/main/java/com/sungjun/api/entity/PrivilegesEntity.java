//package com.sungjun.api.entity;
//
//import com.sungjun.api.entity.BaseEntityAudit;
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "privilege_tbl")
//
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//public class PrivilegesEntity extends BaseEntityAudit {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "privilege_name")
//    private String privilegeName;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getPrivilegeName() {
//        return privilegeName;
//    }
//
//    public void setPrivilegeName(String privilegeName) {
//        this.privilegeName = privilegeName;
//    }
//}
