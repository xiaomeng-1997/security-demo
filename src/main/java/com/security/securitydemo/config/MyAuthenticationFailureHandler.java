package com.security.securitydemo.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 自定义认证失败处理器
 *
 * @author meng
 * @date 2024/05/03
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String localizedMessage = exception.getLocalizedMessage();

        Map<String, Object> result = new HashMap<>();
        result.put("code", -1);
        result.put("message", localizedMessage);

        String json = JSON.toJSONString(result);

        // 设置响应头
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
