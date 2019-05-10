package com.tensquare.configure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Description:
 * @Author: WenChangSheng
 * @Date: Created in 2019/5/10 15:45
 */
@SpringBootApplication
@EnableConfigServer //开启配置服务
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
