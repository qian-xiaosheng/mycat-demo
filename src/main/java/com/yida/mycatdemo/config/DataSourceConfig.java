package com.yida.mycatdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 配置多数据源
 */
@Configuration
public class DataSourceConfig {
    //创建只读数据源
    @Bean(name = "selectDataSource")//只读标识，自定义的
    @ConfigurationProperties("spring.datasource.select")//application.properties中对应属性的前缀
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

     //创建可读写数据源
    @Bean(name = "updateDataSource")//可读写标识，自定义的
    @ConfigurationProperties("spring.datasource.update")//application.properties中对应属性的前缀
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }
}
