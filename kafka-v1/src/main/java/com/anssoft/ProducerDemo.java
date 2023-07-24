package com.anssoft;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Slf4j
public class ProducerDemo {
    public static void main(String[] args) {
        log.info("Test");
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");

        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> producerRecord = new ProducerRecord("second-topic", "hello world");

        producer.send(producerRecord);

        producer.flush();//to send all data and block until done -- synchronous

        producer.close();  //flush and close the producer

    }
}