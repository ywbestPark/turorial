package com.example.tutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "redirect:/hello";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("username", "싸이언");
        return "hello";
    }
}
