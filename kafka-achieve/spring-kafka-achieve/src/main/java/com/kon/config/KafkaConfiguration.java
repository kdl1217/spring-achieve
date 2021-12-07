package com.kon.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * Kafka Configuration
 *
 * @author kon, created on 2021/12/7T15:00.
 * @version 1.0.0-SNAPSHOT
 */
@Slf4j
@Configuration
public class KafkaConfiguration {

    @KafkaListener(topics = "${spring.kafka.topic.self}", concurrency = "${spring.kafka.listener.concurrency}")
    public void processVehicleMode(ConsumerRecord<String, String> record) {
        // key
        String key = record.key();
        String value = record.value();
        // 车辆通知处理器
        log.info("main : key ({}), value({})", key, value);
        log.info("partition : ({}), offset: ({})", record.partition(), record.offset());
    }

}
