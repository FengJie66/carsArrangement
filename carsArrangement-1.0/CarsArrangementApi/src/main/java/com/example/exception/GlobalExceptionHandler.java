package com.example.exception;

import com.example.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 */
@ResponseBody
@ControllerAdvice(annotations = {RestController.class, Controller.class}) //使用了该注解的控制器触发异常
@Slf4j
public class  GlobalExceptionHandler {
    /**
     * 自定义业务异常：CustomException
     */
    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException ex){
        log.error(ex.getMessage());
        return R.error(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R<String> exceptionHandler(Exception ex){
        log.error(ex.getMessage());
        return R.error(ex.getMessage());
    }
}
