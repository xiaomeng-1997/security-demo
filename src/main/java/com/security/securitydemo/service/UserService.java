package com.security.securitydemo.service;

import com.security.securitydemo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;

/**
* @author meng
* @description 针对表【user】的数据库操作Service
* @createDate 2024-05-02 15:52:41
*/
@Mapper
public interface UserService extends IService<User> {

    void saveUserDetails(User user);
}
