package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.UserEntity;

/**
 * @Description:
 * @Author: crx
 * @Create: 14:58 2021/6/1
 */
@Mapper
public interface UserMapper {
    UserEntity queryUser(Long userId, String username);
}
