package com.yida.mycatdemo.aop;


import com.yida.mycatdemo.config.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 配置AOP，拦截调用的方法名，根据方法名来动态路由数据源。
 */
@Component
@Aspect//启动AOP拦截(方法名)
@Lazy(false) //禁用懒加载
@Order(0) //Order设置AOP执行顺序，使之在数据库事务上先执行
public class SwitchDataSourceAop {

    //拦截的方法
    @Before("execution(* com.yida.mycatdemo.service.impl.*.*(..))")
    public void process(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();//获取拦截的方法名
        if (methodName.startsWith("query")||methodName.startsWith("read")||methodName.startsWith("select")||
                methodName.startsWith("get")||methodName.startsWith("list")||methodName.startsWith("count")) {
            DataSourceContextHolder.setDbType("selectDataSource");//只读数据源
        } else {
            DataSourceContextHolder.setDbType("updateDataSource");//可读写数据源
        }
    }
}
