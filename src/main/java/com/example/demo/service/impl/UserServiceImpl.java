package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;

/**
 * @Description:
 * @Author: crx
 * @Create: 14:57 2021/6/1
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity queryUser(Long userId, String username) {
        return userMapper.queryUser(userId, username);
    }
}
