package com.example.tutorial.user.controller;

import com.example.tutorial.user.entity.UserInfo;
import com.example.tutorial.user.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class LoginController {

    @Autowired private UserInfoRepository userRepository; // 글 아래에서 생성할 예정
    @Autowired private BCryptPasswordEncoder passwordEncoder; // 시큐리티에서 빈(Bean) 생성할 예정

    /**
     * 인덱스 페이지
     *
     * @return
     */
    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }

    /**
     * 유저 페이지
     *
     * @return
     */
    @GetMapping("user")
    public String user() {
        return "user";
    }

    /**
     * 로그인 폼 페이지
     *
     * @return
     */
    @GetMapping("loginForm")
    public String loginForm() {
        return "loginForm";
    }

    /**
     * 회원 가입 페이지
     *
     * @return
     */
    @GetMapping("joinForm")
    public String joinForm() {

        return "joinForm";
    }

    /**
     * 회원 가입이 실행되는 부분
     *
     * @param userInfo
     * @return
     */
    @PostMapping("join")
    public String join(UserInfo userInfo) {
        userInfo.setEnabled(true);
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userRepository.save(userInfo);
        return "redirect:/loginForm";
    }
}
