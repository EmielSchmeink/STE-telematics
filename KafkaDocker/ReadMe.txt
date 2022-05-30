To run a producer:
    Internal:
        kafka-console-producer.sh --broker-list kafka:9092 --topic test
    External:
        kafka-console-producer.sh --broker-list 127.0.0.1:9093 --topic test

To run a consumer:
Internal:
    kafka-console-consumer.sh --bootstrap-server kafka:9092 --topic test --from-beginning
External:
    kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9093 --topic test --from-beginning

Whatever text you write and send in the producer shell is going to appear in the consumer shell.