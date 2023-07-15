```

kafka-storage.sh random-uuid

kafka-storage.sh format --config ~/kafka_2.13-3.5.0/config/kraft/server.properties --cluster-id M0m-IX_QTJ2dLy8ZGkoKk

kafka-server-start.sh ~/kafka_2.13-3.5.0/config/kraft/server.properties

kafka-topics.sh --bootstrap-server localhost:9092 --list

kafka-topics.sh --bootstrap-server localhost:9092 --create --topic first-topic


```

- Console commands
```
kafka-console-consumer.sh --bootstrap-server   localhost:9092 --topic first-topic --from-beginning


```