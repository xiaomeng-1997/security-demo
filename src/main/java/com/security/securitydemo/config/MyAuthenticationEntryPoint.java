package com.security.securitydemo.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义未认证处理器
 *
 *
 * @author meng
 * @date 2024/05/03
 */
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String localizedMessage = authException.getLocalizedMessage();

        Map<String, Object> result = new HashMap<>();
        result.put("code", -1);
        result.put("message", "未认证，请先登录");

        String json = JSON.toJSONString(result);

        // 设置响应头
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
