package com.example.tutorial.dto;

import com.example.tutorial.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class BoardForm {
    private String title;
    private String contents;

    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .title(title)
                .contents(contents).build();
    }
}
