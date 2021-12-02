package com.kon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot Launcher
 *
 * @author com.kon, created on 2021/11/19T11:39.
 * @version 1.0.0-SNAPSHOT
 */
@Slf4j
@SpringBootApplication
public class App {

    /**
     * Load the Spring Integration Application Context
     *
     * @param args - command line arguments
     */
    public static void main(final String... args) {

        log.info("\n========================================================="
                + "\n                                                         "
                + "\n          Welcome to Spring Integration!                 "
                + "\n                                                         "
                + "\n    For more information please visit:                   "
                + "\n    https://spring.io/projects/spring-integration        "
                + "\n                                                         "
                + "\n=========================================================" );

        log.info("\n========================================================="
                + "\n                                                          "
                + "\n    This is the MQTT Sample -                             "
                + "\n                                                          "
                + "\n    Please enter some text and press return. The entered  "
                + "\n    Message will be sent to the configured MQTT topic,    "
                + "\n    then again immediately retrieved from the Message     "
                + "\n    Broker and ultimately printed to the command line.    "
                + "\n                                                          "
                + "\n=========================================================" );
        SpringApplication.run(App.class, args);
    }

}
