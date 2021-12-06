package com.example.tutorial.controller;

import com.example.tutorial.dto.BoardForm;
import com.example.tutorial.dto.BoardUpdateDTO;
import com.example.tutorial.entity.BoardEntity;
import com.example.tutorial.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class BoardController {

    @Autowired //스프링부트가 미리 생성해 놓은 객체를 자동으로 연결해 줌
    private BoardRepository boardRepository;

    @GetMapping("/board/lists")
    public String boardLists(Model model){
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        log.info(boardEntityList.toString());
        for (int i=0; i<boardEntityList.size(); i++){
            log.info(boardEntityList.get(i).toDTO().toString());
        }
        model.addAttribute("posts", boardRepository.findAll());
        return "board/lists";
    }

    @GetMapping("/board/new")
    public String boardNew(){
        return "board/new";
    }

    @PostMapping("/board/create")
    @ResponseBody
    public BoardEntity boardCreate(@RequestBody BoardEntity boardEntity){
        log.info(boardEntity.toString());

        //JPA를 이용하여 DB 서버에 저장하기 위해 DTO 클래스를 Entity 클래스로 변환
//        BoardEntity boardEntity = boardForm.toEntity();
//        log.info(boardEntity.toString());

        //JAP에서 제공하는 CrudRepository의 메서드 활용하여 저장
        BoardEntity savedBoardEntity = boardRepository.save(boardEntity);
        log.info(savedBoardEntity.toString());

        return savedBoardEntity;
    }

    @GetMapping("/board/update/view/{id}")
    public String postsUpdate(@PathVariable("id") Long id, Model model){

        log.info("requested update ID : ",id+"");

        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            log.info(boardEntity.toString());
            BoardUpdateDTO boardUpdateDTO = boardEntity.toDTO();
            log.info(boardUpdateDTO.toString());
            model.addAttribute("post", boardUpdateDTO);
        }
        return "board/update";
    }

    @PutMapping("/board/update/{id}")
    @ResponseBody
    public BoardUpdateDTO postsUpdate2(@PathVariable(value = "id") Long id, @RequestBody BoardEntity boardEntity){

//        Long id = boa/dEntity.getId();
        log.info("requested update ID {} ",id+"");
        log.info(boardEntity.toString());

        BoardUpdateDTO boardUpdateDTO = null;
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()){
            boardEntity = boardRepository.save(boardEntity);
            log.info(boardEntity.toString());
            boardUpdateDTO = boardEntity.toDTO();
            log.info(boardUpdateDTO.toString());
        }

        return boardUpdateDTO;

//        return boardRepository.findById(id)
//                .map(boardEntity -> {
//                    boardEntity.builder()
//                            .title(newBoardEntity.getTitle())
//                            .contents(newBoardEntity.getContents).build();
//                    return boardRepository.save(boardEntity);
//                })
//                .orElseGet(() -> {
//                    newBoardEntity.setId(id);
//                    return boardRepository.save(newBoardEntity);
//                });
    }

}
