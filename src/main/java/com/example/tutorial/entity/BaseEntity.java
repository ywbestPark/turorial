package com.example.tutorial.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
public abstract class BaseEntity {

    @Transient
    public static final Sort SORT_BY_CREATED_DATE_DESC =
            Sort.by(Sort.Direction.DESC, "createdDate");

    @Transient
    public static final Sort SORT_BY_MODIFIED_DATE_DESC =
            Sort.by(Sort.Direction.DESC, "modifiedDate");

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

//    private String createdBy = "system";
//    private String modifiedBy = "system";
    private boolean isEnable = true;

    @CreatedBy
    @Column(updatable = false, columnDefinition = "varchar(255) default 'system'")
    private String createdBy;
//
    @LastModifiedBy
    @Column(columnDefinition = "varchar(255) default 'system'")
    private String modifiedBy;

//    @Column(columnDefinition = "varchar(255) default 'true'")
//    private boolean isEnable;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}