package com.kon.handler;

/**
 * 消息Handler
 *
 * @author kon, created on 2021/12/7T14:36.
 * @version 1.0.0-SNAPSHOT
 */
public interface IMessageHandler {

    /**
     * 获取消费信息
     * @param topic   消息类别
     * @param partition 分区
     * @param offset  偏移
     * @param key     Key
     * @param value   Value
     */
    void messageReceive(String topic, int partition, long offset, String key, byte[] value);

}
