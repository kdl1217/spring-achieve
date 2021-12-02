package com.kon.handler;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * note
 *
 * @author kon, created on 2021/11/30T14:59.
 * @version 1.0.0-SNAPSHOT
 */
@Service
public class HelloConsumer {

//    @ServiceActivator(inputChannel = "messageChannel")
    public void consumer(String message) {
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("consumer ->" + message);
    }
}
