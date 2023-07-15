package com.anssoft;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Slf4j
public class ProducerDemoWithKeys {
    public static void main(String[] args) {
        log.info("Test");
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");

        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());

        //Don't use in production
        //properties.setProperty("partitioner.class", RoundRobinPartitioner.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for(int i=0;i<10;i++) {
            String key = "id"+i;
            ProducerRecord<String, String> producerRecord = new ProducerRecord("second-topic", key, "hello world " + i);

            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    //executes every time a record successfully sent or an exception is thrown
                    if (e == null) {
                        //the record was successfully sent
                        log.info("Key: " + key + " | Partition: " + metadata.partition());
                    } else {
                        log.error("Error while producing record");
                    }
                }
            });

        }

        producer.flush();//to send all data and block until done -- synchronous

        producer.close();  //flush and close the producer
    }
}