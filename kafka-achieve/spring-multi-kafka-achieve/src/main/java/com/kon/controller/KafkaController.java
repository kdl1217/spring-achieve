package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/kafka/send")
    public void sendMsg(@RequestParam String msg) {
        this.kafkaTemplate.send(this.topic, msg);
    }
}
