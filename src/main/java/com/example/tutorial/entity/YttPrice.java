package com.example.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class YttPrice extends BaseEntity{
    @EmbeddedId
    private PriceId priceId;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String close;

    @Column(length = 100)
    private String open;

    @Column(length = 100)
    private String high;

    @Column(length = 100)
    private String low;

    @Column(length = 100)
    private String volume;
}
