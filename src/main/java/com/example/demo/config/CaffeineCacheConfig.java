package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.demo.enums.CaffeineCacheEnum;
import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * @Description: 配置缓存管理器（caffeine）
 * @Author: crx
 * @Create: 16:21 2021/4/29
 */
@Configuration
public class CaffeineCacheConfig {

    @Bean("caffeineCacheManager")
    @Primary
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        List<CaffeineCache> caffeineCacheList = new ArrayList<>();
        for (CaffeineCacheEnum cacheEnum : CaffeineCacheEnum.values()) {
            caffeineCacheList.add(new CaffeineCache(
                    cacheEnum.getName(),
                    Caffeine.newBuilder()
                            .expireAfterWrite(cacheEnum.getSecond(), TimeUnit.SECONDS)
                            .initialCapacity(cacheEnum.getInitSize())
                            .maximumSize(cacheEnum.getMaxSize())
                            .build()));
        }
        simpleCacheManager.setCaches(caffeineCacheList);
        return simpleCacheManager;
    }
}
