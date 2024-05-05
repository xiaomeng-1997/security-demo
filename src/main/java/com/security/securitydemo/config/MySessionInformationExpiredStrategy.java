package com.security.securitydemo.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义会话信息过期策略
 *
 * @author meng
 * @date 2024/05/03
 */
public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {

        Map<String, Object> result = new HashMap<>();
        result.put("code", -1);
        result.put("message", "该账号已从其他设备登录");

        String json = JSON.toJSONString(result);

        HttpServletResponse response = event.getResponse();

        // 设置响应头
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);

    }
}
