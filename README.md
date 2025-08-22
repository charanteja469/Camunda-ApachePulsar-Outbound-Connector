# Camunda 8 Apache Pulsar Custom Outbound Connector

The Camunda 8 Pulsar custom outbound connector allows workflow processes within Camunda to publish messages directly to Apache Pulsar topics as part of BPMN execution. Acting as a bridge, the connector enables process automation scenarios to trigger events, notifications, or transactional data flows to any system integrated with Pulsar.

<img width="582" height="227" alt="image" src="https://github.com/user-attachments/assets/300826b8-b19c-4677-8358-fc84e846a487" />

### Apache Pulsar

Apache Pulsar is a cloud-native, open-source distributed messaging and event streaming platform, designed for high-performance, scalability, and multi-tenancy. Originally developed at Yahoo to handle massive messaging needs, Pulsar is now a top-level Apache Software Foundation project. It is well-suited for both real-time message streaming and queue-based workloads


1. Apache Pulsar is a cloud-native, distributed messaging and event streaming platform designed for high performance, durability, and scalability.

2. Its architecture is fully decoupled and decentralized, separating serving (brokers) and storage (BookKeeper) components to eliminate single points of failure.

3. Pulsar supports seamless horizontal scalability, enabling dynamic expansion to millions of topics and consumers with efficient resource balancing.

4. It delivers low-latency, high-throughput messaging with both pub-sub and queue semantics, and supports a variety of data formats and client languages.

5. Pulsar ensures “at-least-once” message delivery with built-in message persistence and geo-replication for robust reliability and disaster recovery

you can download and host NSQ from here   👉🏻 https://pulsar.apache.org/download/


## Test with SaaS and Self-Managed

#### Setting Up the Saas Environment:

1. Navigate to Camunda [SaaS](https://console.camunda.io).
2. Create a cluster using the latest version available.
3. Select your cluster, then go to the `API` section and click `Create new Client`.
4. Ensure the `zeebe` checkbox is selected, then click `Create`.
5. Copy the configuration details displayed under the `Spring Boot` tab.
6. Paste the copied configuration into your `application.properties` file within your project.

#### Setting Up the Self-Managed Environment:

1. Set up the Camunda 8 Self-Managed(https://docs.camunda.io/docs/self-managed/setup/deploy/local/docker-compose/).
2. Cluster endpoint is http://localhost:26500
3. uncomment the properties in test resource folder

   (camunda.client.zeebe.grpc-address=http://localhost:26500)
   
   (camunda.client.zeebe.rest-address=http://localhost:8088)
5. download desktop modeler if requires (https://camunda.com/download/modeler/)

### Launching Your Connector

1. Start your connector by executing `io.camunda.Pulsar.LocalConnectorRuntime` in your development environment.
2. Access the Web Modeler or Desktop Modeler and create a new project.
3. Click on `Create new`, then select `Upload files`. Upload the connector template from the repository you have(https://github.com/charanteja469/Camunda-ApachePulsar-Outbound-Connector/blob/master/element-templates/Apache%20Pulsar%20Connector.json)

 NOTE: if your using Desktop modeler--> go to modeler folder-->resources-->element-templates-->Past the above downloaded pulsar  
       Connector Template

4. In the same folder, create a new BPMN diagram.
5. Design and start a process that incorporates your new connector.

# STEP BY STEP Process to Configure and Use Pulsar Outbound Connector

1. Create a workflow with Start event, Task, End Event
2. select the task and click on element change type and search for pulsar Outbound Connector

  
   <img width="582" height="227" alt="image" src="https://github.com/user-attachments/assets/49105898-e279-4880-840e-879db25c052e" />

3. Configure the Input like (Connection, Topic, Message)

   <img width="666" height="285" alt="image" src="https://github.com/user-attachments/assets/d0150156-3bd5-4f5f-840b-a7c819576e7f" />


   #### Input :

    Pulsar Server URL : pulsar://localhost:6650
   
    Topic : Order
   
    Message : your order delivered successfully...

4. Configure the output Result Expression

<img width="659" height="279" alt="image" src="https://github.com/user-attachments/assets/bf03f490-75c6-40a6-bfec-0e292d71fadd" />


   #### Result Expression :

   {"Status":result}

5. Deploy the process and Start the Process

6. Start the Connector Runtime

   <img width="940" height="495" alt="image" src="https://github.com/user-attachments/assets/8df8775d-e690-49e0-ac68-7246acf7d237" />
   
   <img width="940" height="494" alt="image" src="https://github.com/user-attachments/assets/ecf7b61f-679d-47a4-b9e3-4945aba2d3a8" />

 7. Pulsar Outbound Connector successfully published the message.

  
    <img width="940" height="429" alt="image" src="https://github.com/user-attachments/assets/aad6f184-5fe3-4b60-ab15-48742adebc0a" />
    
    <img width="940" height="430" alt="image" src="https://github.com/user-attachments/assets/2d95d697-25a0-43bc-adef-80c57ab09ea9" />
    
    <img width="2402" height="730" alt="image" src="https://github.com/user-attachments/assets/60a42822-0f1a-4e09-ab6f-fcd3115eccea" />

8. Now you can open the Pulsar consumer application and consume the message.

   here i'm using Pulsar-shell. Run this Command and consume the message

   👉🏻   client consume order -s my-subscription -n 0 -p Earliest

   <img width="940" height="454" alt="image" src="https://github.com/user-attachments/assets/c5232de3-f0b3-4e32-b0b8-34f19a2f30f1" />

## Refer Camunda BPMN File

you can refer the Camunda BPMN file here 👉🏻 https://github.com/charanteja469/Camunda-ApachePulsar-Outbound-Connector/blob/master/src/test/resources/PulsarDemo.bpmn







