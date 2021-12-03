package com.kon.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.util.concurrent.TimeUnit;

/**
 * note
 *
 * @author com.kon, created on 2021/11/30T16:20.
 * @version 1.0.0-SNAPSHOT
 */
public class OutputHandler implements MessageHandler {


    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("consumer message -> " + message.getPayload());
    }
}
