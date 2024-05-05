package com.security.securitydemo.controller;


import com.security.securitydemo.entity.User;
import com.security.securitydemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 *
 * @author meng
 * @date 2024/05/02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN') and authentication.name == 'admin'")
    public List<User> getList() {
        return userService.list();
    }

    @PostMapping("/add")
//    @PreAuthorize("hasAnyRole('USER')")
    @PreAuthorize("hasAnyRole('USER_ADD')")
    public void addUser(@RequestBody User user) {
        log.info("Add user: {}", user);
        userService.saveUserDetails(user);

    }
}