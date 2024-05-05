package com.security.securitydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登录控制器
 *
 * @author meng
 * @date 2024/05/03
 */
@Controller
public class LoginController {


    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
