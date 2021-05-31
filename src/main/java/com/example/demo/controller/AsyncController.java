package com.example.demo.controller;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.SysLog;
import com.example.demo.bean.QueryParam;
import com.example.demo.service.IAsyncService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: crx
 * @Create: 10:26 2021/4/20
 */
@RestController
@RequestMapping({"async", "api"})
@Slf4j
@Validated
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AsyncController {
    @Autowired
    private IAsyncService asyncService;

    @SysLog("异步任务")
    @GetMapping("getAsync")
    public Integer getAsync() {
        log.info("start");
        long millis = System.currentTimeMillis();
//        asyncService.getAsync1();
        Future<Integer> async2 = asyncService.getAsync2();
        FutureTask<Integer> async3 = asyncService.getAsync3();
        FutureTask<Integer> async4 = asyncService.getAsync4();
        Integer value2 = 0;
        Integer value3 = 0;
        Integer value4 = 0;
        try {
            value2 = async2.get();
            value3 = async3.get();
            value4 = async4.get();
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        log.info("over");
        log.info(String.valueOf(System.currentTimeMillis() - millis));
        return value2 + value3 + value4;
    }

    @SysLog
    @GetMapping({"test{uri}", "test"})
    public Object getTest(@Validated QueryParam queryParam, @PathVariable(required = false) String uri) {
        return "success";
    }
}
