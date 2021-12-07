package com.kon.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

/**
 * kafka发送消息结果反馈
 *
 * @author kon, created on 2021/12/7T15:05.
 * @version 1.0.0-SNAPSHOT
 */
@Slf4j
public class KafkaSendResultHandler<K, V> implements ProducerListener<K, V> {

    @Override
    public void onSuccess(ProducerRecord<K, V> producerRecord, RecordMetadata recordMetadata) {
        log.info("send ({}) message Success!!!", producerRecord.value());
    }

    @Override
    public void onError(ProducerRecord<K, V> producerRecord, RecordMetadata recordMetadata, Exception exception) {
        log.error("send ({}) message error!!!", producerRecord.value(), exception);
    }
}
