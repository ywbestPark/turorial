package com.example.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZthmError extends BaseEntity{
    @Id // 키값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 자동 생성
    private Long id;

    @Column(length = 5000)
    private String errorMessage;

}
