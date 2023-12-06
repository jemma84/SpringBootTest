package com.example.demo.handler;

import com.example.demo.dto.ErrorApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler
    public ModelAndView defaultErrorHandler(Exception e) {
        log.debug("Got exception: {}", e.getMessage());

        ErrorApi error = new ErrorApi();
        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setStatus(HttpStatus.BAD_REQUEST.toString());
        error.setMessage(HttpStatus.BAD_REQUEST.toString());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorApi", error);
        modelAndView.setViewName("error");
        return modelAndView;
    }


}
