//package com.sungjun.api.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//import org.hibernate.annotations.ColumnDefault;
//import org.hibernate.annotations.DynamicInsert;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Date;
//
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//
//@NoArgsConstructor
//@AllArgsConstructor
//@DynamicInsert
//@ToString
//public abstract class BaseEntityAudit implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @JsonIgnore
//    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = "created_by", referencedColumnName = "id")
//    private UserEntity createdBy;
//
//    @JsonIgnore
//    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = "updated_by", referencedColumnName = "id")
//    private UserEntity updatedBy;
//
//    @CreatedDate
//    @Column(name = "created_at", nullable = false)
//    private Date createdAt;
//
//    @LastModifiedDate
//    @Column(name = "updated_at", nullable = false)
//    private Date updatedAt;
//
//    @ColumnDefault("true")
//    @Column(name = "status", nullable = false)
//    private Boolean status;
//
//    public UserEntity getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(UserEntity createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public UserEntity getUpdatedBy() {
//        return updatedBy;
//    }
//
//    public void setUpdatedBy(UserEntity updatedBy) {
//        this.updatedBy = updatedBy;
//    }
//
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public Boolean getStatus() {
//        return status;
//    }
//
//    public void setStatus(Boolean status) {
//        this.status = status;
//    }
//}