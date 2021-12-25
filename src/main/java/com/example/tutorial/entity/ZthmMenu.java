package com.example.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZthmMenu extends BaseEntity{
    @Id // 키값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 자동 생성
    private Long id;

    private Long parentId;
    private int level;
    private int menuOrder;
    private String menuName;
    private String menuPath;

}
