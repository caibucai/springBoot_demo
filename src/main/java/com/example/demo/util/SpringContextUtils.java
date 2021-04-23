package com.example.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description: springContext工具类
 * @Author: crx
 * @Create: 11:14 2021/4/21
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
    //定义静态的ApplicationContext成员对象
    private static ApplicationContext applicationContext;

    //重写setApplicationContext方法，把参数中的ApplicationContext对象赋值给类静态成员
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //定义get方法，参数为Class，调用上下文的getBean方法获取容器中的指定对象
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    //定义get方法，参数为String，调用上下文的getBean方法获取容器中的指定对象
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    //定义get方法，参数为String和Class，调用上下文的getBean方法获取容器中的指定对象
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }
}
