package com.kon.produce;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.tomcat.util.buf.HexUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 生产者模型
 *
 * @author kon, created on 2021/12/7T14:45.
 * @version 1.0.0-SNAPSHOT
 */
@Slf4j
public class Producer {

    /**
     * 生产者
     */
    private final KafkaProducer<String, byte[]> producer;

    /**
     * 构造函数
     */
    public Producer(){
        Properties kafkaProps = new Properties();
        //broker的地址清单 bootstrap.servers
        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.31.138:9092,192.168.31.139:9092,192.168.31.140:9092") ;
        //all表示所有partition都写入才确认，1表示主partition写入即确认，0表示发送后不确认 acks
        kafkaProps.put(ProducerConfig.ACKS_CONFIG, "all");
        //失败后重发的次数 retries
        kafkaProps.put(ProducerConfig.RETRIES_CONFIG, 3);
        //批量发送的字节数 batch.size
        kafkaProps.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        //延迟发送时间,原理就是把原本需要多次发送的小batch，通过引入延时的方式合并成大batch发送，减少了网络传输的压力，从而提升吞吐量。当然，也会引入延时 linger.ms
        kafkaProps.put(ProducerConfig.LINGER_MS_CONFIG, 0);
        //缓存大小 buffer.memory
        kafkaProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        //将key序列化成字节数组 key.serializer
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        //将value序列化成字节数组 value.serializer
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
        producer = new KafkaProducer<>(kafkaProps);
    }

    /**
     * 发送消息
     *
     * @param topic   主题
     * @param content 内容
     * @return offset偏移量
     */
    public long send(String topic, byte[] content) {
        //key是为了确定丢到哪个partition
        // String key = String.valueOf(new Random().nextInt(100));

        try {
            Future<RecordMetadata> future =
                    producer.send(new ProducerRecord<>(topic, content),
                            (metadata, exception) -> log.debug("Kafka send callback metadata:  " + metadata));


            long offset = future.get().offset();
            log.debug("Success send, offset: " + offset);

            return offset;
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return -1;
    }

    /**
     * 批量发送
     *
     * @param topic    主题
     * @param contents 内容
     */
    public List<Long> batchSend(String topic, List<byte[]> contents) {

        if (null == contents || 0 == contents.size()) {
            throw new IllegalArgumentException();
        }

        List<Long> offsetList = new ArrayList<>(contents.size());

        try {
            for (byte[] content : contents) {
                String key = new Random().nextInt(100) + "";
                Future<RecordMetadata> future =
                        producer.send(new ProducerRecord<>(topic, key, content),
                                (metadata, exception) -> log.debug("callback  " + metadata));

                // future.get();
                long offset = future.get().offset();
                log.debug("success send ,offset " + offset);
                offsetList.add(offset);
            }
        } catch (Exception e) {
            for (int i = offsetList.size(); i < contents.size(); i++) {
                offsetList.add(-1L);
            }
            log.error(e.getMessage());
        }
        return offsetList;
    }

    /**
     * 关闭释放生产者
     */
    public void close() {
        producer.close();
    }


    /**********************************测试例子*******start**********************************/
    public static void main(String[] args) {
        // 获取生产者对象
        Producer producer = new Producer() ;
        // 设置Topic
        String topic = "topic" ;
        while (true) {
            long currentTime = System.currentTimeMillis() ;
            // 发送消息
            producer.send(topic, HexUtils.fromHexString(String.valueOf(currentTime))) ;
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
