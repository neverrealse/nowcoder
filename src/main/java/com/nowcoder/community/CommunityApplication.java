package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication//配置文件  自动扫描：配置类及其子包下

public class CommunityApplication {

    public static void main(String[] args) {

        //创建spring容器、启动tomcat
        SpringApplication.run(CommunityApplication.class, args);
    }

}
