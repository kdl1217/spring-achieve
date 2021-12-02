package com.kon.handler;

import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

/**
 * note
 *
 * @author kon, created on 2021/11/30T16:21.
 * @version 1.0.0-SNAPSHOT
 */
public class InputChannel extends DirectChannel {

    public boolean send(String message) {
        System.out.println("send message ->" + message);
        return super.send(new GenericMessage<>(message));
    }

    @Override
    public boolean send(Message<?> message) {
        return super.send(message);
    }
}
