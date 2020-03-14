package com.yida.mycatdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yida.mycatdemo.dao")
public class MycatDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycatDemoApplication.class, args);
    }

}
