package com.example.demo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: crx
 * @Create: 15:00 2021/4/29
 */
@RestController
@RequestMapping("cache")
@Slf4j
public class CacheController {

    @Autowired
    private CacheManager cacheManager;

    @SneakyThrows
    @Cacheable(value = "cache1", key = "#key1", condition = "#key2.equals('2')")
    @GetMapping("getCache1")
    public Object getCache1(String key1, String key2) {
        Thread.sleep(1000);
        log.info("cache1，keys：{},{}", key1, key2);
        return "cache1";
    }

    @SneakyThrows
    @Cacheable(value = "cache2", key = "#key", cacheManager = "caffeineCacheManager")
    @GetMapping("getCache2")
    public Object getCache2(String key) {
        Thread.sleep(1000);
        log.info("cache2，key：{}", key);
        return "cache2";
    }

    @CacheEvict(value = "cache1", key = "#key")
    @GetMapping("deleteCache")
    public Object deleteCacheByName(String key) {
        return "删除缓存cache1下的key：" + key;
    }

    @CacheEvict(value = "cache1", allEntries = true)
    @GetMapping("deleteAllCache")
    public Object deleteAllCache() {
        return "删除缓存cache1下所有的key";
    }

    @GetMapping("deleteAll")
    public Object deleteAll() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        for (String cacheName : cacheNames) {
            cacheManager.getCache(cacheName).clear();
        }
        return "删除所有缓存";
    }
}
