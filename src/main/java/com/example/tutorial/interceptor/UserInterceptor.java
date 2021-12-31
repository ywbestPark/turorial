package com.example.tutorial.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
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