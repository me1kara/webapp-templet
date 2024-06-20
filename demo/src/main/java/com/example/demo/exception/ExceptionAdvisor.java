package com.example.demo.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionAdvisor {

    @ExceptionHandler(ClassNotFoundException.class)
    public String error(Exception ex){
        log.error("error occur = {} ", ex.getMessage());
        return "error"; //error 페이지로 핸들링(설정해야됨)
    }
}
