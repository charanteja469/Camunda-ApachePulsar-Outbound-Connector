package io.camunda.pulsar.dto;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.Schema;

public class Pulsar {


    public String publishMessage(String serverurl, String topic, String message) {

        try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl(serverurl)
                    .build();

            Producer<String> producer = client.newProducer(Schema.STRING)
                    .topic(topic)
                    .create();

            producer.send(message);
            producer.close();
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    return "Ok";

    }
}
