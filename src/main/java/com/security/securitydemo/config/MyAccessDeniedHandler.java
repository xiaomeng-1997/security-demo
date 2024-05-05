package com.security.securitydemo.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 自定义AccessDeniedHandler，用于处理权限不足的异常情况。
 *
 * @author meng
 * @date 2024/05/03
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String localizedMessage = accessDeniedException.getLocalizedMessage();

        Map<String, Object> result = new HashMap<>();
        result.put("code", -1);
        result.put("message", "权限不足");
        result.put("data", localizedMessage);

        String json = JSON.toJSONString(result);

        // 设置响应头
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
