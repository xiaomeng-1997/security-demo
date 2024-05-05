package com.security.securitydemo.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.security.securitydemo.entity.User;
import com.security.securitydemo.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;


/**
 * dbuser 详细信息管理器
 *
 * @author meng
 * @date 2024/05/02
 */
@Configuration
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    private static final Logger log = LoggerFactory.getLogger(DBUserDetailsManager.class);
    @Resource
    private UserMapper userMapper;


    /**
     * 更新密码
     *
     * @param user        用户
     * @param newPassword 新密码
     * @return {@link UserDetails }
     */
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    /**
     * 创建用户
     *
     * @param userDetails 用户详细信息
     */
    @Override
    public void createUser(UserDetails userDetails) {
        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEnabled(Boolean.TRUE);
        log.info("user:{}", user);
        userMapper.insert(user);
    }

    /**
     * 更新用户
     *
     * @param userDetails 用户详细信息
     */
    @Override
    public void updateUser(UserDetails userDetails) {
    }

    /**
     * 删除用户
     *
     * @param username 用户名
     */
    @Override
    public void deleteUser(String username) {

    }

    /**
     * 更改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    /**
     * 用户存在
     *
     * @param username 用户名
     * @return boolean
     */
    @Override
    public boolean userExists(String username) {
        return false;
    }

    /**
     * 按用户名加载用户
     * 从数据库中获取用户信息
     *
     * @param username 用户名
     * @return {@link UserDetails }
     * @throws UsernameNotFoundException 找不到用户名异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>(User.class).eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);
        log.info("-------start-------");
        log.info("user:{}", user);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
           /* boolean t = user.getEnabled();
            List<GrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(() -> "USER_LIST");
            authorities.add(() -> "USER_ADD");

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    t,
                    true,
                    true,
                    true,
                    authorities
            );*/
            return org.springframework.security.core.userdetails.User
                    // 用户名
                    .withUsername(user.getUsername())
                    // 密码
                    .password(user.getPassword())
                    // 是否启用
                    .disabled(!user.getEnabled())
                    // 是否过期
                    .credentialsExpired(false)
                    // 是否锁定
                    .accountLocked(false)
                    // 角色
                    .roles("ADMIN")
                    // 权限  会把 roles 转换为权限，roles 配置失效
                    .authorities("USER_ADD", "USER_UPDATE")
                    .build();

        }
    }

}
