package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.IUserService;

/**
 * @Description:
 * @Author: crx
 * @Create: 15:10 2021/6/1
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("getUserById")
    public UserEntity getUserById(Long userId, String username) {
        return userService.queryUser(userId, username);
    }
}
