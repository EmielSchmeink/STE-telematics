import java.util.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

public class Producer {

    public static void main(String[] args) {

        //Hard coded values for now
        //TODO: Change these variables to read data from CAN-BUS
        String topicName = "Topic";
        //String key = "Key1";
        String value = "Value1";
        String bootstrapservers = "localhost:9092, localhost:9093";

        Properties props = new Properties();
        //change this local host to our server
        //first local host is main user, second local host is available if anything happens to first host
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapservers);
        //StringSerializer will probably be changed to IntSerializer, depends on our needs
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //first string is based on key, second is based on value
        KafkaProducer<String,String> first_producer = new KafkaProducer<String, String>(props);  
        ProducerRecord<String, String> record=new ProducerRecord<String, String>(topicName, value);  

        first_producer.send(record);
        first_producer.flush();
        first_producer.close();

        
    }
}
