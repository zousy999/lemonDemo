package com.lemon.exceptionhandler.service.impl;

import com.lemon.exceptionhandler.entity.User;
import com.lemon.exceptionhandler.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author lemon
 * @description 用户业务实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String addUser(User user) {
        // 直接编写业务逻辑
        return "success";
    }
}
