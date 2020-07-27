package com.hx.config;

import com.alibaba.fastjson.JSON;
import com.hx.bean.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Result responseBody = new Result();

        responseBody.setStatus(400);
        responseBody.setMessage("Login Failure!");

        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}
