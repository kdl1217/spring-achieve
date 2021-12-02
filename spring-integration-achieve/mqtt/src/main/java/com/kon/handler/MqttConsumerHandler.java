package com.kon.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * note
 *
 * @author kon, created on 2021/11/19T14:41.
 * @version 1.0.0-SNAPSHOT
 */
@Slf4j
public class MqttConsumerHandler implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        log.info("consumer message: {}", message.getPayload().toString());
    }
}
