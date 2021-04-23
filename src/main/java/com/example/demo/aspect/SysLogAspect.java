package com.example.demo.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.example.demo.annotation.SysLog;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 日志注解切面
 * @Author: crx
 * @Create: 11:20 2021/4/23
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {
    // 配置织入点
    @Pointcut("@annotation(com.example.demo.annotation.SysLog)")
    public void sysLogPointCut() {

    }

    /**
     * 环绕增强方法
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("sysLogPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法的签名，joinPoint中有相应的签名信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 通过方法的签名可以获取方法本身
        Method method = signature.getMethod();
        // 获取方法上的@SysLog注解
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if (null != sysLog) {
            // 获取注解上的内容
            String value = sysLog.value();
            // 通过joinPoint获取方法的实际参数的值
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                //to do ...
            }
            // 获取方法的名称
            String methodName = method.getName();
            log.info("方法名：{}", methodName);
        }
        long beginTime = System.currentTimeMillis();
        // 调用proceed方法进行方法的实际执行
        Object result = joinPoint.proceed();
        // 执行时长
        long time = System.currentTimeMillis() - beginTime;
        log.info("执行时长：{}", time);
        // 最后如果完成过返回实际方法的结果
        return result;
    }

//    /**
//     * 后置增强，处理完请求后执行
//     *
//     * @param joinPoint
//     * @param jsonResult
//     */
//    @AfterReturning(pointcut = "sysLogPointCut()", returning = "jsonResult")
//    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
//        log.info("执行结果：{}", jsonResult);
//    }
//
//    /**
//     * 异常抛出增强
//     *
//     * @param e
//     */
//    @AfterThrowing(pointcut = "sysLogPointCut()", throwing = "e")
//    public void doAfterThrowing(Exception e) {
//        log.info("抛出的异常：", e);
//    }
//
//    /**
//     * 前置增强方法
//     */
//    @Before("sysLogPointCut()")
//    public void doBefore() {
//        log.info("前置增强");
//    }
}
