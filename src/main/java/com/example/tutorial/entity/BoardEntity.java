package com.example.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity //DB가 해당 객체를 인식 가능
@AllArgsConstructor
@ToString
public class BoardEntity {

    @Id // 키값 지정
    @GeneratedValue // 자동 생성
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

    public BoardEntity() {
    }
}
