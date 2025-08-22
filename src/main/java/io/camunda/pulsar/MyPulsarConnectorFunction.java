package io.camunda.pulsar;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.generator.java.annotation.ElementTemplate;
import io.camunda.pulsar.dto.MyPulsarConnectorRequest;
import io.camunda.pulsar.dto.MyPulsarConnectorResult;
import io.camunda.pulsar.dto.Pulsar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OutboundConnector(
    name = "Apache Pulsar Outbound Connector",
    inputVariables = {"url", "Topic", "message"},
    type = "io.camunda:template:1")
@ElementTemplate(
    id = "Template_1oepc2m",
    name = "Apache Pulsar Outbound Connector",
    version = 1,
    description = "This custom connector links Camunda 8 to Apache Pulsar, allowing workflows to publish messages.",
    inputDataClass = MyPulsarConnectorRequest.class)
public class MyPulsarConnectorFunction implements OutboundConnectorFunction {

  private static final Logger LOGGER = LoggerFactory.getLogger(MyPulsarConnectorFunction.class);

  @Override
  public Object execute(OutboundConnectorContext context) {
    final var connectorRequest = context.bindVariables(MyPulsarConnectorRequest.class);
    return executeConnector(connectorRequest);
  }

  private MyPulsarConnectorResult executeConnector(final MyPulsarConnectorRequest connectorRequest) {

    String serverUrl=connectorRequest.url();
    String topic = connectorRequest.Topic();
    String message= connectorRequest.message();

    Pulsar pulsar = new Pulsar();
    String result=pulsar.publishMessage(serverUrl,topic,message);

    System.out.println("Pulsar Server URL : "+serverUrl);
    System.out.println("Topic : "+topic);
    System.out.println("Message : "+message);
    System.out.println("status : "+result);


    return new MyPulsarConnectorResult("Message Published Status: " + result);
  }
}
