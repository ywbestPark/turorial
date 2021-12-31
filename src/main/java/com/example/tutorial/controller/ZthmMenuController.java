package com.example.tutorial.controller;

import com.example.tutorial.service.ZthmMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@RequestMapping("/menu")
@Controller
@Slf4j
public class ZthmMenuController {
    private final ZthmMenuService zthmMenuService;
    private final HttpSession httpSession;

    @RequestMapping("/")
    public String getMenu(){
        return zthmMenuService.getMenu();
    }
}
