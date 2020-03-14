package com.yida.mycatdemo.config;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 用于多线程之间，保存或读取数据源标识
 */
@Component //注入到Spring的Ioc容器中
@Lazy(false) //禁用懒加载
public class DataSourceContextHolder {
    //采用ThreadLocal保存本地多个数据源，用于多线程安全的数据共享
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    //设置数据源标识
    public static void setDbType(String dbType) {
        contextHolder.set(dbType);
    }

    //获取数据源标识
    public static String getDbType() {
        return contextHolder.get();
    }

    //清除数据源标识
    public static void clearDbType() {
        contextHolder.remove();
    }
}
