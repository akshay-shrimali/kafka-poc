package com.anssoft;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

@Slf4j
public class ConsumerDemo {
    public static void main(String[] args) {
        log.info("Test");

        String groupId = "my-java-application";
        String topic = "second-topic";

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");

        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());

        properties.put("auto.offset.reset", "earliest");
        properties.put("group.id", groupId);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Arrays.asList(topic));

        while(true) {
            log.info("Polling");

            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));

            for(ConsumerRecord<String, String> record: consumerRecords) {
                log.info("key: {}, value: {}", record.key(), record.value());
                log.info("partition: {}, offset: {}", record.partition(), record.offset());
            }
        }



    }
}