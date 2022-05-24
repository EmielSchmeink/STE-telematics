import java.lang.System.LoggerFinder;

import java.util.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;

public class KafkaConsumer {
    
    public static void main(String[] args) {
        
        final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
        final String bootstrapservers = "localhost:9092, localhost:9093";


        Properties p = new Properties();
        //change this local host to our server
        //first local host is main user, second local host is available if anything happens to first host
        p.put("bootstrap.servers", bootstrapservers);
        //StringSerializer will probably be changed to IntSerializer, depends on our needs
        p.put("key.serializer", "org.apache.kafka.common.serialization.StringDeserializer");
        p.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    }
}
