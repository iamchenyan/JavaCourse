package com.cms.cycms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.cms.cycms.dao.mappers")
@EnableCaching
public class CyCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyCmsApplication.class, args);
    }

}
