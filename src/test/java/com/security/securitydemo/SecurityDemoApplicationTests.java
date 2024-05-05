package com.security.securitydemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

@SpringBootTest
class SecurityDemoApplicationTests {

    /**
     * 上下文加载
     */
    @Test
    void contextLoads() {
        String user = "user";
        String role_user = "role_user";
        System.out.println(user.contains(role_user));
    }

    /**
     * 测试密码编码器
     */
    @Test
    void testPasswordEncoder() {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);

        String encode = encoder.encode("123456");
        System.out.println("加密后的密码：" + encode);

        Assert.isTrue(encoder.matches("123456", encode), "密码匹配");

    }

}
