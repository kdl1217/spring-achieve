package com.kon;

import com.kon.framework.swagger.annotation.EnableKonSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

/**
 * SpringBoot Launcher
 *
 * @author kon, created on 2021/12/1T14:00.
 * @version 1.0.0-SNAPSHOT
 */
@EnableKonSwagger
@EnableIntegration
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
