package com.example.tutorial.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HelloController {

    @GetMapping("/home")
    public String index() {
        return "redirect:/hello";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("username", "싸이언");
        return "hello";
    }

    @GetMapping("/layout/test")
    public String home() {
        return "layouttest";
    }
}
