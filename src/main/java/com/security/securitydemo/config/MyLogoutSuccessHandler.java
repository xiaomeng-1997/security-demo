package com.security.securitydemo.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义退出登录成功处理器
 *
 * @author meng
 * @date 2024/05/03
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", "退出登录成功");

        String json = JSON.toJSONString(result);

        // 设置响应头
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
    }
}
