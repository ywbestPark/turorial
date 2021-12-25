package com.example.tutorial.controller;

import com.example.tutorial.service.ZthmMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/menu")
public class ZthmMenuController {
    private final ZthmMenuService zthmMenuService;

    @RequestMapping("/")
    public String getMenu(){
        return zthmMenuService.getMenu();
    }
}
