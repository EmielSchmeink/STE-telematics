<h1> Starting up and using the Kafka cluster of brokers </h1>

<ol>
    <li>
        <h3><strong>Initilization of the Kafka container(s)</strong></h3>
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
        <h3><strong>Starting up Kafka</strong></h3>
        <ol>
            <li>
                Go to the containers menu in Docker Desktop.<br>
                <img src="ReadMe_resources\docker_containers_menu.png" alt="Missing image">
            </li>
            <li>
                Open a terminal in Kafka's container.
                <img src="ReadMe_resources\docker_kafka_cli_button.png" alt="Missing image">
            </li>
            <li>
                Execute the following command in the terminal to land in the right directory.
                <blockquotes>cd ./opt/bitnami/kafka</blockquotes>
            </li>
            <li>
                <blockquotes></blockquotes>
            </li>
            <li>
                <blockquotes></blockquotes>
            </li>
            <li>
                <blockquotes></blockquotes>
            </li>
            <li>
                <blockquotes></blockquotes>
            </li>
        </ol>
    </li>
    <li>
        <h3><strong>Kafka container files</strong></h3>
    </li>
    <li>
        <h3><strong>Networking</strong></h3>
    </li>
    <li>
        <h3><strong>Running basic producers and consumers</strong></h3>
        <p>
        Whatever text you write and send in the producer shell is going to appear in the consumer shell. Note that the topic name and/or the port could be different.
        </p>
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
                        <blockquote>kafka-console-consumer.sh --bootstrap-server kafka:9092 --topic test --from-beginning</blockquote>
                    </li>
                    <li>
                        External
                        <blockquote>kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9093 --topic test --from-beginning</blockquote>
                    </li>
                </ul>
                The flag <em>--from-beginning</em> makes the consumer read the whole log of messages. Suppose the consumer's task is only to read the messages received after its initialization, the flag should be omitted.
            </li>
        </ul>
    </li>
    <li>
        <h3><strong>Taking down the Kafka container(s)</strong></h3>
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