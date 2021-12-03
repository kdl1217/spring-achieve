package com.kon.handler;

import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;

/**
 * note
 *
 * @author com.kon, created on 2021/11/30T16:55.
 * @version 1.0.0-SNAPSHOT
 */
@MessageEndpoint
public class EndPoint {

//    @Filter(inputChannel = "inputChannel", outputChannel = "transformChannel")
//    public boolean process(String message) {
//        return "World".equals(message);
//    }
//
//    @Transformer(inputChannel = "transformChannel",  outputChannel = "consumerChannel")
//    public String transform(String message) {
//        return "Hello ".concat(message);
//    }
//
//    @ServiceActivator(inputChannel = "consumerChannel")
//    public void ac(String message) {
//        System.out.println(message);
//    }
}
