package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @description:
 * @author: wenchangsheng
 * @date: created in 2019/4/20
 */
@SpringBootApplication
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    /**
     * 将id生成器放到容器中，因为本模块要用，其他模块不一定用，所以谁用谁引入
     *
     * @return
     */
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
