package com.example.tutorial.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
        log.info("[ preHandle ]");
        log.info("test {}"+request.getAttribute("userInfo"));
        SecurityContext securityContext = SecurityContextHolder.getContext();
        log.info("securityContext {} "+securityContext.toString());
        if(securityContext.getAuthentication()!=null){

            if(securityContext.getAuthentication().getPrincipal() instanceof DefaultOAuth2User){
                DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) securityContext.getAuthentication().getPrincipal();
                Map<String,Object> kakao_account = (Map<String, Object>)defaultOAuth2User.getAttribute("kakao_account");
                Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");
                log.info("username : "+profile.get("nickname"));
                log.info("role : "+securityContext.getAuthentication().getAuthorities());
            }else{
                log.info("username : "+securityContext.getAuthentication().getName());
                log.info("role : "+securityContext.getAuthentication().getAuthorities());
            }

        }


        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("[ postHandle ]");
        CsrfToken csrfToken = (CsrfToken) request.getAttribute("-csrf");
        log.info("return csrf token in postHandle {} ", csrfToken);
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object object,
            Exception ex
    ) throws Exception {
        log.info("afterCompletion");
    }
}