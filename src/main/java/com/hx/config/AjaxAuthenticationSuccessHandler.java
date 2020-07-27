package com.hx.config;

import com.alibaba.fastjson.JSON;
import com.hx.bean.Result;
import com.hx.utils.JwtUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        Result responseBody = new Result();
        responseBody.setStatus(200);
        responseBody.setMessage("Login Success!");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = JwtUtils.createJWT(principal);
        responseBody.setToken(token);
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}

