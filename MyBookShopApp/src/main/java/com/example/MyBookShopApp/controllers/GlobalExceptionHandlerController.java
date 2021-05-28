package com.example.MyBookShopApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public String handleNullPointerException(Exception e) {
        Logger.getLogger(GlobalExceptionHandlerController.class.getName()).info("404 is working...");
        return "404";
    }


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String handleAllException(Exception e) {
        Logger.getLogger(GlobalExceptionHandlerController.class.getName()).info("500 is working...");
        return "404";
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleResourceNotFoundException() {
        return "404";
    }
}
