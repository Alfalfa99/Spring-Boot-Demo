package com.alfalfa.demo.config;

import com.alfalfa.demo.domain.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 登录异常处理逻辑,直接转至全局异常进行处理
 */
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        RespBean rb = RespBean.error("500",null,null);
        e.printStackTrace();
        if (e instanceof AccountExpiredException) {
            //账号过期
             rb.setMsg("账号过期，操作失败!");
        } else if (e instanceof BadCredentialsException) {
            //密码错误
             rb.setMsg("密码错误!");
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
             rb.setMsg("密码过期!");
        } else if (e instanceof DisabledException) {
            //账号不可用
             rb.setMsg("账号不可用!");
        } else if (e instanceof LockedException) {
            //账号锁定
             rb.setMsg("账号锁定!");
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
             rb.setMsg("用户不存在!");
        } else {
            rb.setMsg("你触发了一些奇怪的错误，请联系客服哟!");
        }
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write(new ObjectMapper().writeValueAsString(rb));
        out.flush();
        out.close();
    }
}
