import java.util.*;
import java.time.Duration;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Consumer {
    
    public static void main(String[] args) {
        
        final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
        //change this local host to our server
        final String bootstrapservers = "localhost:9092, localhost:9093";
        String grp_id = "group_id";
        String topic = "topic";


        Properties p = new Properties();
        //first local host is main user, second local host is available if anything happens to first host
        p.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapservers);
        //StringSerializer will probably be changed to IntSerializer, depends on our needs
        p.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.setProperty(ConsumerConfig.GROUP_ID_CONFIG, grp_id);
        p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


        //creating the consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(p);

        //subscribing consumer to topics
        consumer.subscribe(Arrays.asList(topic));

        //polling for new data
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

        
        for(ConsumerRecord<String, String> record: records){
            logger.info("key: "+record.key()+ ", Value:"+record.value());
            logger.info("Partition: "+record.partition() +", offset: "+record.offset());
        }
        
        
    }
}
