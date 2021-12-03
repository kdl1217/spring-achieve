package com.kon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

/**
 * note
 *
 * @author com.kon, created on 2021/11/30T14:24.
 * @version 1.0.0-SNAPSHOT
 */
@EnableIntegration
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
