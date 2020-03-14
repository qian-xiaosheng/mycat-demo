package com.yida.mycatdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态路由数据源
 */
@Component //注入到Spring的Ioc容器中
@Primary //优先使用自定义的动态路由数据源
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Autowired
    @Qualifier("selectDataSource")
    private DataSource selectDataSource;//只读数据源

    @Autowired
    @Qualifier("updateDataSource")
    private DataSource upadateDataSource;//可读写数据源

    /**
     * 这个是主要方法，返回的是生效的数据源名称
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String dbType = DataSourceContextHolder.getDbType();
        System.out.println("当前生效的数据源名称为："+dbType);
        return dbType;
    }

    /**
     * 配置多数据源信息
     */
    @Override
    public void afterPropertiesSet() {
        //设置多目标数据源
        Map<Object,Object> map = new HashMap<>();
        map.put("selectDataSource", selectDataSource);
        map.put("updateDataSource", upadateDataSource);
        setTargetDataSources(map);
        //设置默认的目标数据源
        setDefaultTargetDataSource(upadateDataSource);
        super.afterPropertiesSet();//调用父类方法，设定多目标数据源
    }
}
