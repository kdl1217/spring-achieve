package com.kon.config;

import com.kon.handler.KafkaSendResultHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Kafka Configuration
 *
 * @author kon, created on 2021/12/7T15:00.
 * @version 1.0.0-SNAPSHOT
 */
@Slf4j
@Configuration
public class KafkaConfiguration {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaSecondTemplate;

    /**
     * 配置生产者监听器，也可以不配置
     */
    @Bean
    public void producerListener() {
        kafkaTemplate.setProducerListener(new KafkaSendResultHandler<>());
        kafkaSecondTemplate.setProducerListener(new KafkaSendResultHandler<>());
    }

    @KafkaListener(topics = "${spring.kafka.topic.self}", concurrency = "${spring.kafka.listener.concurrency}",
            containerFactory = "kafkaListenerContainerFactory")
    public void processMainMode(ConsumerRecord<String, String> record) {
        // key
        String key = record.key();
        String value = record.value();
        // 车辆通知处理器
        log.info("main : key ({}), value({})", key, value);
        log.info("partition : ({}), offset: ({})", record.partition(), record.offset());
    }

    @KafkaListener(topics = "${third-party.kafka.second.topic.second-kon}", concurrency = "${spring.kafka.listener.concurrency}",
            containerFactory = "kafkaSecondContainerFactory")
    public void processSecondMode(ConsumerRecord<String, String> record) {
        // key
        String key = record.key();
        String value = record.value();
        // 车辆通知处理器
        log.info("second : key ({}), value({})", key, value);
    }

}
