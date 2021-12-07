package com.kon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot Launcher
 *
 * @author kon, created on 2021/12/3T11:33.
 * @version 1.0.0-SNAPSHOT
 */
@MapperScan("com.kon.dao")
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
