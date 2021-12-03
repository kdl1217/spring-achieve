package com.kon.handler;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MqttPublisherHandler
 *
 * @author com.kon, created on 2021/11/19T14:19.
 * @version 1.0.0-SNAPSHOT
 */
public class MqttPublisherHandler extends MqttPahoMessageHandler {

    private static final Map<String, List<String>> CLIENT_ID_MAP = new HashMap<>();

    public static MqttPublisherHandler init(String clientId, String topic, MqttPahoClientFactory clientFactory) {
        List<String> topics = CLIENT_ID_MAP.get(clientId);
        if (null == topics) {
            topics = Collections.singletonList(topic);
        } else {
            topics.add(topic);
        }
        CLIENT_ID_MAP.put(clientId, topics);
        return new MqttPublisherHandler(clientId, topic, clientFactory);
    }

    public MqttPublisherHandler(String clientId, String topic, MqttPahoClientFactory clientFactory) {
        super(clientId, clientFactory);
        super.setAsync(true);
        super.setDefaultTopic(topic);
    }

    public void sendMessage(String topic, String message) {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes(StandardCharsets.UTF_8));
        super.publish(topic, mqttMessage, null);
    }

}
