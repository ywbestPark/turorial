package com.example.tutorial.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class MyErrorCotroller implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status!=null){
            int statusCode = Integer.valueOf(status.toString());
            if(statusCode== HttpStatus.NOT_FOUND.value()){
                return "error/404";
            }
            if(statusCode== HttpStatus.FORBIDDEN.value()){
                return "error/403";
            }
        }
        return "error";
    }
}
