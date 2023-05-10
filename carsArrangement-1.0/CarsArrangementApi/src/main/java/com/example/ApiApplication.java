package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

/**
 * @version 1.0
 */
@SpringBootApplication
@MapperScan("com.example.mapper")
@IntegrationComponentScan(basePackages = "com.example.mqtt") //这里添加，不然无法自动注入
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class,args);
    }
}
