package com.example.demo.bean;

import lombok.Getter;

/**
 * @Description:
 * @Author: crx
 * @Create: 16:56 2021/4/29
 */
@Getter
public enum CaffeineCacheEnum {
    CACHE1("cache1", 50, 20000, 300),

    CACHE2("cache2", 10, 10000, 200),
    ;

    private String name;

    private int second;

    private long maxSize;

    private int initSize;

    CaffeineCacheEnum(String name, int second, long maxSize, int initSize) {
        this.name = name;
        this.second = second;
        this.maxSize = maxSize;
        this.initSize = initSize;
    }
}
