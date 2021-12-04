package com.example.tutorial.controller;

import com.example.tutorial.dto.BoardForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class BoardController {

    @GetMapping("/board/new")
    public String boardNew(){
        return "board/new";
    }

    @PostMapping("/board/create")
    public String boardCreate(BoardForm boardForm){
        log.info(boardForm.toString());
        return "board/new";
    }

}
