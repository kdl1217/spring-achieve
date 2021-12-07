package com.kon.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.HexUtils;

/**
 * 消息订阅
 *
 * @author kon, created on 2021/12/7T14:37.
 * @version 1.0.0-SNAPSHOT
 */
@Slf4j
public class MessageHandler implements IMessageHandler {

    @Override
    public void messageReceive(String topic, int partition, long offset, String key, byte[] value) {
        log.info("key: {}, value: {}", key, HexUtils.toHexString(value));
    }
}
