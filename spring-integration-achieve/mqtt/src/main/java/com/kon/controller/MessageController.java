package com.kon.controller;

import com.kon.handler.MqttPublisherHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * note
 *
 * @author com.kon, created on 2021/11/19T14:50.
 * @version 1.0.0-SNAPSHOT
 */
@Slf4j
@RestController
public class MessageController {

    @Autowired
    private MqttPublisherHandler mqttPublisherHandler;

    @GetMapping("/sendMessage/{topic}/{message}")
    public String sendMessage(@PathVariable String topic, @PathVariable String message) {
        mqttPublisherHandler.sendMessage(topic, message);
        return "topic :" + topic + "message :" + message;
    }

}
