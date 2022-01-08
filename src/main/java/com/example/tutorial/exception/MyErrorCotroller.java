package com.example.tutorial.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Controller
public class MyErrorCotroller implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        model.addAttribute("path", request.getAttribute("javax.servlet.error.request_uri"));
        model.addAttribute("timestamp", new Date());

        if(status!=null){
            model.addAttribute("status", status);
            int statusCode = Integer.valueOf(status.toString());
            if(statusCode== HttpStatus.NOT_FOUND.value()){
                return "error/404";
            }
            if(statusCode== HttpStatus.FORBIDDEN.value()){
                return "error/403";
            }
        }

        Object exceptionObject = request.getAttribute("javax.servlet.error.exception");
        if(exceptionObject!=null){
            Throwable e = ((Exception) exceptionObject).getCause();
            model.addAttribute("exception", e.getClass().getName());
            model.addAttribute("message", e.getMessage());
        }

        return "error";
    }
}
