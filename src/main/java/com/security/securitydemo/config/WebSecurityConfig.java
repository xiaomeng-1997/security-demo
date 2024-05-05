package com.security.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity // 启用Spring Security
@EnableMethodSecurity // 启用方法级安全
public class WebSecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(
//                User.withDefaultPasswordEncoder().username("admin").password("123456").roles("ROOT").build());
//        return manager;
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        // 创建基于数据库的用户信息管理器
//        return new DBUserDetailsManager();
//    }


    /**
     * 过滤链
     *
     * @param http 网址
     * @return {@link SecurityFilterChain }
     * @throws Exception 例外
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 配置URL权限
        return http.authorizeHttpRequests(authorize -> authorize
                        // 允许所有静态资源
                        //.requestMatchers("/user/list").hasAuthority("USER_LIST")
                        // 允许所有登录请求
                        // .requestMatchers("/user/add").hasAuthority("USER_ADD")
                        // 允许所有注册请求
                        // .requestMatchers("/user/**").hasRole("ADMIN")
                        // 允许所有请求
                        .anyRequest()
                        // 需要认证
                        .authenticated()
                )
                // 配置登录页面
                .formLogin(form -> form
                        // 配置登录页面的URL，默认值是"/login"
                        .loginPage("/login").permitAll()
                        // 自定义用户名参数，默认值是"username"
                        .usernameParameter("username")
                        // 配置自定义密码参数，默认值是"password"
                        .passwordParameter("password")
                        // 登录失败后的URL，默认值是"/login?error"
                        .failureUrl("/login?error")
                        // 登录成功后的处理
                        .successHandler(new MyAuthenticationSuccessHandler())
                        // 登录失败后的处理
                        .failureHandler(new MyAuthenticationFailureHandler())
                )
                // 配置登出页面
                .logout(logout -> logout
                        // 配置自定义的登出成功处理器
                        .logoutSuccessHandler(new MyLogoutSuccessHandler())
                )
                // 配置自定义的未认证处理器
                .exceptionHandling(exceptions -> exceptions
                        // 配置自定义的未授权处理器
                        .authenticationEntryPoint(new MyAuthenticationEntryPoint())
                        .accessDeniedHandler(new MyAccessDeniedHandler())
                )
                // 配置会话管理器
                .sessionManagement(session -> session
                        // 配置会话创建策略
                        .maximumSessions(1)
                        // 配置会话超时策略
                        .expiredSessionStrategy(new MySessionInformationExpiredStrategy())
                )
                // 配置自定义的访问决策器，解决跨域问题
                .cors(Customizer.withDefaults())
                // 禁用CSRF保护
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }


}
