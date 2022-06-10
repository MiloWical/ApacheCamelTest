package git.milowical;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.component.pulsar.PulsarComponent;
import org.apache.camel.component.pulsar.PulsarConfiguration;
import org.apache.camel.component.pulsar.PulsarEndpoint;
import org.apache.camel.component.pulsar.PulsarEndpointConfigurer;

public class PulsarReaderRouter extends RouteBuilder {

  private String uri = "pulsar:persistent://localhost:6650/public/default/outbound-topic";
  private PulsarEndpoint pulsarEndpoint;

  public PulsarReaderRouter(CamelContext camelContext)
  {
    PulsarConfiguration pulsarConfiguration = new PulsarConfiguration();
    pulsarConfiguration.setServiceUrl("pulsar://localhost:6650/");

    PulsarComponent pulsarComponent = new PulsarComponent(camelContext);

    pulsarEndpoint = new PulsarEndpoint(uri,pulsarComponent);

    pulsarEndpoint.setPulsarConfiguration(pulsarConfiguration);
    pulsarEndpoint.setPersistence("persistent");
    pulsarEndpoint.setTenant("localhost:6650");
    pulsarEndpoint.setNamespace("public/default");
    pulsarEndpoint.setTopic("outbound-topic");

    pulsarEndpoint.build();

    System.out.println("***** Pulsar Configuration Service URL: " + pulsarEndpoint.getPulsarConfiguration().getServiceUrl());

    pulsarComponent.setConfiguration(pulsarEndpoint.getPulsarConfiguration());
  }

  @Override
  public void configure() throws Exception {

    // 

    System.out.println("Pulsar Endpoint: " + pulsarEndpoint.getUri());

    from(pulsarEndpoint)
        .log("Message received from Pulsar : ${body}")
        .log("    on the topic ${headers[topic_name]}")
        .log("    published at ${headers[publish_time]}")
        .log("    from the producer ${headers[producer_name]}")
        .setHeader(KafkaConstants.KEY, constant("Pulsar")) // Key of the message
        .to("kafka:testTopic?brokers=localhost:9092");
  }

}
