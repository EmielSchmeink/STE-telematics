cd /
cd ./opt/bitnami/kafka
uuid=`./bin/kafka-storage.sh random-uuid`
./bin/kafka-storage.sh format -t $uuid -c ./config/kraft/server.properties
./bin/kafka-topics.sh --create --topic topic1 --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092