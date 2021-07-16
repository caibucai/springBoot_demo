package com.example.demo.service;

import com.example.demo.entity.UserEntity;

public interface IUserService {

    UserEntity queryUser(Long userId, String username);

}
