package com.security.securitydemo.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 索引控制器
 *
 * @author meng
 * @date 2024/05/01
 */
@RestController
public class IndexController {

    /**
     * 首页
     *
     * @return {@link String }
     */
    @GetMapping("/")
    public Map<String, Object> index() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        Authentication authentication = securityContext.getAuthentication();

        // 获取登录用户的信息
        Object principal = authentication.getPrincipal();
        // 获取登录用户的凭据
        Object credentials = authentication.getCredentials();
        // 获取登录用户的权限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        // 获取其他相关信息
        Object details = authentication.getDetails();
        // 获取登录用户名
        String name = authentication.getName();

        Map<String, Object> result = new HashMap<>();
        result.put("principal", principal);
        result.put("credentials", credentials);
        result.put("authorities", authorities);
        result.put("details", details);
        result.put("name", name);
        return result;
    }
}
