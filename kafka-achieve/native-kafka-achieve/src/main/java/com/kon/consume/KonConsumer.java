package com.kon.consume;

import com.kon.handler.IMessageHandler;
import com.kon.handler.MessageHandler;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.*;

/**
 * note
 *
 * @author kon, created on 2021/12/7T14:35.
 * @version 1.0.0-SNAPSHOT
 */
public class KConsumer {

    /**
     * 消费者
     */
    private KafkaConsumer<String, byte[]> consumer;

    /**
     * 订阅消息Handler
     */
    private IMessageHandler messageHandler;

    /**
     * 消息读取
     */
    private KConsumer.MsgReader msgReader;

    /**
     * 订阅主题
     */
    private List<String> topicList;

    /**
     * 构造函数
     *
     * @param topic 订阅主题
     */
    public KConsumer(String topic, IMessageHandler messageHandler) {
        if (!StringUtils.hasLength(topic)) {
            throw new IllegalArgumentException();
        }

        this.messageHandler = messageHandler;
        this.msgReader = new KConsumer.MsgReader();

        Properties props = new Properties();
        //设置消费的Topic组
        this.topicList = Collections.singletonList(topic);
        // 该地址是集群的子集，用来探测集群。 bootstrap.servers
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.31.138:9092,192.168.31.139:9092,192.168.31.140:9092");
        // consumer的分组id group.id
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kong-2");
        // 自动提交offsets enable.auto.commit
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        // 自动提交设置提交时间间隔
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        // 设置使用最开始的offset偏移量为该group.id的最早。如果不设置，则会是latest即该topic最新一个消息的offset
        // 如果采用latest，消费者只能得道其启动后，生产者生产的消息 auto.offset.reset
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // Consumer向集群发送自己的心跳，超时则认为Consumer已经死了，kafka会把它的分区分配给其他进程 session.timeout.ms
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        // 反序列化器 key.deserializer   value.deserializer
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        //拉取循环中的处理数据量 max.poll.records
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "50");

        this.consumer = new KafkaConsumer<>(props);
        consumer.subscribe(topicList);
    }

    /**
     * 启动消息订阅
     */
    public void start(){
        this.msgReader.start();
    }

    private class MsgReader extends Thread {
        private MsgReader() {
        }

        @Override
        public void run() {
            while(true) {
                try {
                    ConsumerRecords<String, byte[]> records = KConsumer.this.consumer.poll(Duration.ofMillis(100L));

                    for (ConsumerRecord<String, byte[]> record : records) {
                        try {
                            if (KConsumer.this.messageHandler != null) {
                                KConsumer.this.messageHandler.messageReceive(record.topic(), record.partition(), record.offset(), record.key(), record.value());
                            }
                        } catch (Exception var5) {
                            var5.printStackTrace();
                        }
                    }
                } catch (Exception var6) {
                    var6.printStackTrace();
                }
            }

        }
    }


    /**********************************测试例子*******start**********************************/
    public static void main(String[] args) {
        MessageHandler msgHandler = new MessageHandler();
        // Topic
        String topic = "topic" ;
        // 获取消费者
        KConsumer consumer = new KConsumer(topic, msgHandler) ;
        //启动订阅器
        consumer.start();
    }

}
