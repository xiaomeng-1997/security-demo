package com.security.securitydemo.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义登录成功处理器
 *
 * @author meng
 * @date 2024/05/03
 *///@Configuration
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获取用户信息
        Object principal = authentication.getPrincipal();
        // 获取凭证信息
        Object credentials = authentication.getCredentials();
        // 获取权限信息
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", "登录成功");
        result.put("data", principal);

        String json = JSON.toJSONString(result);

        // 设置响应头
        response.setContentType("application/json;charset=UTF-8");
        log.warn(json);
        response.getWriter().println(json);
    }

}
