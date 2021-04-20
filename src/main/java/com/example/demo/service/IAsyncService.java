package com.example.demo.service;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @Author: crx
 * @Create: 10:26 2021/4/20
 */
public interface IAsyncService {
    void getAsync1();

    Future<Integer> getAsync2();

    FutureTask<Integer> getAsync3();

    FutureTask<Integer> getAsync4();
}
