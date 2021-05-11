package com.example.demo.exception;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import com.example.demo.commons.ResponseMsg;


/**
 * @Description: 全局异常处理
 * @Author: crx
 * @Create: 16:21 2021/4/29
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class GlobalExceptionHandler {

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMsg<Object> handleBindException(HttpServletRequest req, BindException e, HandlerMethod method) {
        String message = e.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("；"));
        return ResponseMsg.forMsg(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, null, message);
    }

}
