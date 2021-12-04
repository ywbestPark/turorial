package com.example.tutorial.dto;

import com.example.tutorial.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class BoardForm {
    private String title;
    private String contents;

    public BoardEntity toEntity() {
        return new BoardEntity(null, title, contents);
    }
}
