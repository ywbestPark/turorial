package com.example.tutorial.dto;

import com.example.tutorial.entity.BoardEntity;
import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class BoardUpdateDTO {
    private long id;
    private String title;
    private String contents;

    @Builder
    public BoardUpdateDTO(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .title(title)
                .contents(contents).build();
    }


}
