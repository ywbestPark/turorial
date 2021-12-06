package com.example.tutorial.entity;

import com.example.tutorial.dto.BoardUpdateDTO;
import lombok.*;

import javax.persistence.*;

@Entity //DB가 해당 객체를 인식 가능
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BoardEntity {

    @Id // 키값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 자동 생성
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

    @Builder
    public BoardEntity(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public BoardUpdateDTO toDTO() {
        return BoardUpdateDTO.builder()
                .id(id)
                .title(title)
                .contents(contents).build();
    }
}
