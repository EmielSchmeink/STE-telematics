import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.Producer;

public class ProducerKafka {
    // Hard coded for now
     final String TOPIC;
     private final Producer<String, String> producer;
    // To be changed to kafka cluster servers when up and running
    private final static String BOOTSTRAP_SERVERS =
            "localhost:9092,localhost:9093,localhost:9094";

    public ProducerKafka(final Producer<String, String> producer,final String topic) {                     
        this.producer = producer;
        TOPIC = topic;
}

    public Future<RecordMetadata> produce(final String message) {
        //Perform data handling here to extract needed key and value to send to cluster


        final ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC, key, value);
        return producer.send(producerRecord);
}

    public void shutdown() {
        producer.close();
    }

    //handles data from input file
    // will be changed according to our needs when connection with teensy controller is done
    public static Properties loadProperties(String fileName) throws IOException {
        final Properties envProps = new Properties();
        final FileInputStream input = new FileInputStream(fileName);
        envProps.load(input);
        input.close();

        return envProps;
    }


     public void printMetadata(final Collection<Future<RecordMetadata>> metadata,
                              final String fileName) {
        System.out.println("Offsets and timestamps committed in batch from " + fileName);
        metadata.forEach(m -> {
            try {
                final RecordMetadata recordMetadata = m.get();
                System.out.println("Record written to offset " + recordMetadata.offset() + " timestamp " + recordMetadata.timestamp());
            } catch (InterruptedException | ExecutionException e) {
                if (e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

            
    public static void main(String[] args) throws Exception {
        // Do not fully understand this line probably need to be adapted
        final Properties props = ProducerKafka.loadProperties(args[0]);
        // Probably would need to be changed depending on how data is inputted
        final String topic = props.getProperty("output.topic.name");
        final Producer<String, String> producer = new KafkaProducer<>(props);
        final ProducerKafka producerApp = new ProducerKafka(producer, topic);   
        
        // Probably would need to be changed depending on how data is inputted
        String filePath = args[1];

        try {
            List<String> linesToProduce = Files.readAllLines(Paths.get(filePath));
            List<Future<RecordMetadata>> metadata = linesToProduce.stream()
                    .filter(l -> !l.trim().isEmpty())
                    .map(producerApp::produce)
                    .collect(Collectors.toList());
            producerApp.printMetadata(metadata, filePath);

        } catch (IOException e) {
            System.err.printf("Error reading file %s due to %s %n", filePath, e);
        }
        finally {
            producerApp.shutdown();
        }
    }
}
