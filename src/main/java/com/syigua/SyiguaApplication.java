package com.syigua;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangfuliang
 *  启动类
 */
@SpringBootApplication
@MapperScan("com.syigua.mapper")
public class SyiguaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyiguaApplication.class, args);
    }

}
