package com.example.tutorial.entity;

import org.springframework.data.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedBy
    @Column(updatable = false, columnDefinition = "varchar(255) default 'John Snow'")
    private String createdBy;

    @LastModifiedBy
    @Column(columnDefinition = "varchar(255) default 'John Snow'")
    private String modifiedBy;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}