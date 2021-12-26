package com.example.tutorial.user.controller;

import com.example.tutorial.user.entity.UserInfo;
import com.example.tutorial.user.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Slf4j
@Controller
public class LoginController {

    @Autowired private UserInfoRepository userRepository; // 글 아래에서 생성할 예정
    @Autowired private BCryptPasswordEncoder passwordEncoder; // 시큐리티에서 빈(Bean) 생성할 예정

    @GetMapping({"", "/"})
    public String index(Model model) {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        log.info("username {} "+securityContext.getAuthentication().getName());
//        log.info("role {} "+securityContext.getAuthentication().getAuthorities());
//
//        model.addAttribute("message", "You are logged in as "
//                + securityContext.getAuthentication().getName());

        return "redirect:/index.html";
    }

    @GetMapping("user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String user(Principal principal) {
        log.info("loginUserName {} "+principal.getName());
        log.info("loginUserInfo {} "+principal);
        return "user";
    }

    @GetMapping("loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/signout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/loginForm";
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
        userInfo.setEnabled(false);
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userRepository.save(userInfo);
        return "redirect:/loginForm";
    }
}
