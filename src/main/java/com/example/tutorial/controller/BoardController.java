package com.example.tutorial.controller;

import com.example.tutorial.dto.BoardForm;
import com.example.tutorial.entity.BoardEntity;
import com.example.tutorial.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class BoardController {

    @Autowired //스프링부트가 미리 생성해 놓은 객체를 자동으로 연결해 줌
    private BoardRepository boardRepository;

    @GetMapping("/board/new")
    public String boardNew(){
        return "board/new";
    }

    @PostMapping("/board/create")
    public String boardCreate(BoardForm boardForm){
        log.info(boardForm.toString());

        //JPA를 이용하여 DB 서버에 저장하기 위해 DTO 클래스를 Entity 클래스로 변환
        BoardEntity boardEntity = boardForm.toEntity();
        log.info(boardEntity.toString());

        //JAP에서 제공하는 CrudRepository의 메서드 활용하여 저장
        BoardEntity savedBoardEntity = boardRepository.save(boardEntity);
        log.info(savedBoardEntity.toString());

        return "board/new";
    }

}
