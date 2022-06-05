docker exec -it kafka /bin/sh -c "kafka-console-consumer.sh --bootstrap-server kafka:9092 --topic topic1 --from-beginning"
Read-Host -Prompt "Press any key to continue"