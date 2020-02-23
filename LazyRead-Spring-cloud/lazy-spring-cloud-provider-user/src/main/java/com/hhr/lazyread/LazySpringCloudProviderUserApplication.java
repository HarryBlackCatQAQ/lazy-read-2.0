package com.hhr.lazyread;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@EnableAutoConfiguration(exclude = {
//        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
//})
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})


@MapperScan(basePackages = "com.hhr.lazyread.dao*")
@SpringBootApplication
public class LazySpringCloudProviderUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(LazySpringCloudProviderUserApplication.class, args);
    }

}
