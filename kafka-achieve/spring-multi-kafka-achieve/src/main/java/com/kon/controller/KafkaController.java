package com.kon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kafka测试控制层
 *
 * @author kon, created on 2021/12/7T15:13.
 * @version 1.0.0-SNAPSHOT
 */
@RestController
public class KafkaController {

    @Value("${spring.kafka.topic.self}")
    String topic;

    @Value("${third-party.kafka.second.topic.second-kon}")
    String secondTopic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaSecondTemplate;

    @GetMapping("/kafka/send/{type}")
    public void sendMsg(@PathVariable Integer type, @RequestParam String msg) {
        if (1 == type) {
            this.kafkaTemplate.send(this.topic, msg);
        } else {
            this.kafkaSecondTemplate.send(this.secondTopic, msg);
        }
    }
}
