package com.example.tutorial.exception;

import com.example.tutorial.entity.ZthmError;
import com.example.tutorial.repository.ZthmErrorRepository;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class MyErrorController implements ErrorController {

    private final ZthmErrorRepository zthmErrorRepository;

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model){

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        model.addAttribute("path", request.getAttribute("javax.servlet.error.request_uri"));
        model.addAttribute("timestamp", new Date());

        int statusCode = 0;
        if(status!=null){
            model.addAttribute("status", status);
            statusCode = Integer.valueOf(status.toString());
        }

        Object exceptionObject = request.getAttribute("javax.servlet.error.exception");
        String errorClassName = "";
        String message = "";
        if(exceptionObject!=null){
            Throwable e = ((Exception) exceptionObject).getCause();
            model.addAttribute("exception", e.getClass().getName());
            model.addAttribute("message", e.getMessage());
            errorClassName = e.getClass().getName();
            message = e.getMessage();
        }

        zthmErrorRepository.save(ZthmError.builder()
                .errorMessage("MyErrorController Error : " +
                        "path : "+request.getAttribute("javax.servlet.error.request_uri")+", "
                        +"Status Code : "+statusCode+", "
                        +"Error Class : "+errorClassName+", "
                        +"message : "+message)
                .build());

        if(statusCode== HttpStatus.NOT_FOUND.value()){
            return "error/404";
        }
        if(statusCode== HttpStatus.FORBIDDEN.value()){
            return "error/403";
        }

        return "error";
    }
}
