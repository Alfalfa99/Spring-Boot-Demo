package com.alfalfa.demo.exception;

import com.alfalfa.demo.domain.RespBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public RespBean sqlException(Exception e) {
        //debug模式下查找错误
        e.printStackTrace();
        if (e instanceof NullPointerException) {
            return RespBean.error("500","出现了一个空指针异常，操作失败!",null);
        } else if (e instanceof DuplicateKeyException) {
            return RespBean.error("500","用户名已存在!",null);
        } else if (e instanceof SQLIntegrityConstraintViolationException) {
            return RespBean.error("500","该数据有关联数据，操作失败!",null);
        }
        return RespBean.error("500","你触发了一些奇怪的异常，请联系客服哟!","null");
    }
}