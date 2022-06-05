<h1> Starting up and using the Kafka cluster of brokers </h1>

<ol>
    <li>
        <a id=Initialization_of_the_Kafka_container(s)><h3><strong>Initilization of the Kafka container(s)</strong></h3></a>
        <ol>
            <li>
                Make sure to have Docker Desktop installed.
            </li>
            <li>
                Open an instance of Powershell on Windows or a terminal on Linux.
            </li>
            <li>
                Navigate to the folder where docker-compose.yml is located. Currently in our project it is in KafkaDocker.
            </li>
            <li>
                Execute the following:
                <blockquote>docker-compose -f docker-compose.yml up -d</blockquote>
                This line tells docker-compose to create and start containers following the instructions from a file called docker-compose.yml.
            </li>
            <li>
                The service is now (hopefuly) up and running. Proceed to the next step.
            </li>
        </ol>
    </li>
    <li>
        <a id=Starting_up_Kafka><h3><strong>Starting up Kafka</strong></h3></a>
        <ol>
            <li>
                Go to the containers menu in Docker Desktop.<br>
                <img src="ReadMe_resources\docker_containers_menu.png" alt="Missing image">
            </li>
            <li>
                Open a terminal in Kafka's container. More than 1 terminal can be open at the same time.
                <img src="ReadMe_resources\docker_kafka_cli_button.png" alt="Missing image">
                <strong>
                    Alternatively, to skip the previous 2 steps, a terminal to a shell of the kafka container can be opened by running the following command in a shell of your choice on the host:
                </strong>
                <blockquote>docker exec -it kafka /bin/sh</blockquote>
                The terminal in which you wrote the command is now connected to a shell of the kafka container.
            </li>
            <li>
                Execute the following command in the terminal to land in the right directory. <br>
                <blockquote>cd ./opt/bitnami/kafka</blockquote>
            </li>
            <li>
                Generate a random uuid for a cluster with this line.
                <blockquote>./bin/kafka-storage.sh random-uuid </blockquote>
            </li>
            <li>
                Replace &ltuuid&gt with the uuid received after executing the above command. It formats the storage directories. The line runs a cluster in single-node mode.
                <blockquote>./bin/kafka-storage.sh format -t &ltuuid&gt -c ./config/kraft/server.properties</blockquote>
            </li>
            <li>
                To start the Kafka server on a node run the line below. Note that each node has to be initialized like that in its own terminal.
                <blockquote>./bin/kafka-server-start.sh ./config/kraft/server.properties</blockquote>
            </li>
            <li>
                To create a topic named topic1:
                <blockquote>./bin/kafka-topics.sh --create --topic topic1 --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092</blockquote>
                One can connect to port 9092 to perform administrative operations or produce or consume data. The port could be, of course, configured in a different way.
            </li>
        </ol>
    </li>
    <li>
        <a id=Kafka_container_files><h3><strong>Kafka container files</strong></h3></a>
    </li>
    <li>
        <a id=Networking><h3><strong>Networking</strong></h3></a>
    </li>
    <li>
        <a id=Running_basic_producers_and_consumers><h3><strong>Running basic producers and consumers</strong></h3></a>
        <p>
            Whatever text you write and send in the producer shell is going to appear in the consumer shell. Note that the topic name and/or the port could be different.<br>
            Each consumer and producer are best created in a separate Kafka terminal which can be opened following steps 1. and 2. in section <a href="#Starting_up_Kafka">2. Starting up Kafka</a>. 
        </p>
        <p>
            Scripts have been created to automatically create producers and consumers. They are currently located in <code> KafkaDocker\producer_consumer </code>
        </pp>
        <ul>
            <li>
                <h4><strong>Producer</strong></h4>
                <ul>
                    <li>
                        Internal
                        <blockquote>kafka-console-producer.sh --broker-list kafka:9092 --topic topic1</blockquote>
                    </li>
                    <li>
                        External
                        <blockquote>kafka-console-producer.sh --broker-list 127.0.0.1:9093 --topic topic1</blockquote>
                    </li>
                </ul>
            </li>
            <li>
                <h4><strong>Consumer</strong></h4>
                <ul>
                    <li>
                        Internal
                        <blockquote>kafka-console-consumer.sh --bootstrap-server kafka:9092 --topic topic1 --from-beginning</blockquote>
                    </li>
                    <li>
                        External
                        <blockquote>kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9093 --topic topic1 --from-beginning</blockquote>
                    </li>
                </ul>
                The flag <em>--from-beginning</em> makes the consumer read the whole log of messages. Suppose the consumer's task is only to read the messages received after its initialization, the flag should be omitted.
            </li>
        </ul>
    </li>
    <li>
        <a id=Taking_down_the_Kafka_container(s)><h3><strong>Taking down the Kafka container(s)</strong></h3></a>
        <ol>
            <li>
                Open an instance of Powershell on Windows or a terminal on Linux.
            </li>
            <li>
                Navigate to the folder where docker-compose.yml is located. Currently in our project it is in KafkaDocker.
            </li>
            <li>
                Execute the following:
                <blockquote>docker-compose -f docker-compose.yml down</blockquote>
                This line tells docker-compose to bring down containers following the instructions from a file called docker-compose.yml.
            </li>
            <li>
                The services are now stopped.
            </li>
        </ol>
    </li>
</ol>