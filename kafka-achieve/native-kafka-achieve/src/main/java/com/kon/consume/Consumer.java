package com.kon.consume;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * note
 *
 * @author kon, created on 2021/12/7T14:27.
 * @version 1.0.0-SNAPSHOT
 */
@Slf4j
public class Consumer {

    /**
     * 消费者
     */
    private KafkaConsumer<String, byte[]> consumer;

    /**
     * 订阅主题
     */
    private List<String> topicList;

    /**
     * 构造函数
     *
     * @param topic 订阅主题
     */
    public Consumer(String topic) {
        if (!StringUtils.hasLength(topic)) {
            throw new IllegalArgumentException();
        }

        Properties props = new Properties();
        //设置消费的Topic组
        this.topicList = Collections.singletonList(topic);
        // 该地址是集群的子集，用来探测集群。 bootstrap.servers
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.31.138:9092,192.168.31.139:9092,192.168.31.140:9092");
        // consumer的分组id group.id
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kon-1");
        // 自动提交offsets enable.auto.commit
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
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

//        props.put(ConsumerConfig.METRICS_RECORDING_LEVEL_CONFIG,"INFO") ;

        this.consumer = new KafkaConsumer<>(props);
        consumer.subscribe(topicList);
    }

    /**
     * 批量消费
     * @return
     */
    public List<byte[]> batchReceive() {
        List<byte[]> msgList = new ArrayList<>();

        try {
            ConsumerRecords<String, byte[]> records = consumer.poll(500);
            if (null == records) {
                return null;
            }

            for (TopicPartition partition : records.partitions()) {
                List<ConsumerRecord<String, byte[]>> partitionRecords = records.records(partition);
                for (ConsumerRecord<String, byte[]> record : partitionRecords) {
                    // log.debug("now consumer the message it's offset is :" + record.offset() + " and the value is :" + record.value());
                    msgList.add(record.value());

                }
                long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                // log.debug("now commit the partition[ " + partition.partition() + "] offset");
                consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        if (0 == msgList.size()) {
            return null;
        }

        return msgList;
    }

    /**
     * 关闭释放消费者
     */
    public void close() {
        consumer.close();
    }


    /**********************************测试例子*******start**********************************/
    public static void main(String[] args) {
        // Topic
        String topic = "topic";
        // 获取消费者
        Consumer consumer = new Consumer(topic) ;
        while (true) {
            // 拉取数据
            List<byte[]> msgList = consumer.batchReceive() ;
            // 没有消息时等待
            if (null == msgList){
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // 处理消息
                for (byte[] msg : msgList) {
                    System.out.println(HexUtils.toHexString(msg));
                }
            }
        }
    }
}
