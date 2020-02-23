package com.hhr.lazyread;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@MapperScan(basePackages = "com.hhr.lazyread.dao*")
public class LazySpringCloudMybatisMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(LazySpringCloudMybatisMysqlApplication.class, args);
    }

}
