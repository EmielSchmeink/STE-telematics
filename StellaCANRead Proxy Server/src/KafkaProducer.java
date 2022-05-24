import java.util.*;
import org.apache.kafka.clients.producer.*;

public class KafkaProducer {

    public static void main(String[] args) {

        //Hard coded values for now
        //TODO: Change these variables to read data from CAN-BUS
        String topicName = "Topic";
        String key = "Key1";
        String value = "Value1";

        Properties props = new Properties();
        //change this local host to our server
        //first local host is main user, second local host is available if anything happens to first host
        props.put("bootstrap.servers", "localhost:9092, localhost:9093");
        //StringSerializer will probably be changed to IntSerializer, depends on our needs
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

        //first string is based on key, second is based on value
        Producer<String, String> record = new ProducerRecord<>(topicName,key,value);
        producer.send(record);
        producer.close();

        
    }
}
