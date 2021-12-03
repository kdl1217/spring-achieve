package com.kon.config;

import com.kon.handler.MqttConsumerHandler;
import com.kon.handler.MqttPublisherHandler;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.stream.CharacterStreamReadingMessageSource;

/**
 * Mqtt Configuration
 *
 * @author com.kon, created on 2021/11/19T11:43.
 * @version 1.0.0-SNAPSHOT
 */
@Slf4j
@Configuration
public class MqttConfig {

    public static final String PUBLISH_CLIENT = "siSamplePublisher";

    public static final String CONSUMER_CLIENT = "siSampleConsumer";

    public static final String TOPIC = "siSampleTopic";

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[] { "tcp://com.kon:1883" });
        options.setUserName("guest");
        options.setPassword("guest".toCharArray());
        factory.setConnectionOptions(options);
        return factory;
    }

    /**
     * publisher
     * @return
     */
    @Bean
    public IntegrationFlow mqttOutFlow() {
        log.info("loading mqttOutFlow...");
        return IntegrationFlows.from(CharacterStreamReadingMessageSource.stdin(),
                e -> e.poller(Pollers.fixedDelay(1000)))
                .transform(p -> p + " sent to MQTT")
                .handle(mqttPublisherHandler())
                .get();
    }

    @Bean
    public MqttPublisherHandler mqttPublisherHandler() {
        return MqttPublisherHandler.init(PUBLISH_CLIENT, TOPIC, mqttClientFactory());
    }

    @Bean
    public IntegrationFlow mqttInFlow() {
        log.info("loading mqttInFlow...");
        return IntegrationFlows.from(mqttInbound())
                .transform(p -> p + ", received from MQTT")
                .handle(new MqttConsumerHandler())
                .get();
    }

    @Bean
    public MessageProducerSupport mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(CONSUMER_CLIENT,
                mqttClientFactory(), TOPIC);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        return adapter;
    }
}
