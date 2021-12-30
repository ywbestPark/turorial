package com.example.tutorial.controller;

import com.example.tutorial.service.ZthmMenuService;
import com.example.tutorial.user.dto.SessionUser;
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
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("userInfo");
//        SessionUser sessionUser = (SessionUser) SessionScopeUtil.getAttribute("userInfo");
        log.info("userInfo From HttpSession", sessionUser.toString());
        return zthmMenuService.getMenu(httpSession);
    }
}
