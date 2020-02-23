package com.hhr.lazyread;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.hhr.lazyread.dao*")
@SpringBootApplication
public class LazySpringCloudProviderBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(LazySpringCloudProviderBookApplication.class, args);
    }

}
