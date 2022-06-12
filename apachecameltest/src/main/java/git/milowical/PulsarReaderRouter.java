package git.milowical;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.component.pulsar.PulsarComponent;
import org.apache.camel.component.pulsar.PulsarConfiguration;

public class PulsarReaderRouter extends RouteBuilder {

  private String pulsarUri;
  private String kafkaUri;

  // TODO: Submit a PR to the Pulsar Camel component repo for this.
  // 
  // The regex that the PulsarPath object uses employs greedy matching, so namespaces
  // like "public/default" were getting split incorrectly, causing the URI-based
  // PulsarComponent auto-configuration to fail.
  //
  // See: https://github.com/apache/camel/blob/c6015f6d6303d029df374fc615c22ecc581e3479/components/camel-pulsar/src/main/java/org/apache/camel/component/pulsar/utils/PulsarPath.java#L23-L40
  private static final Pattern PATTERN = Pattern.compile("^(persistent|non-persistent):?/?/(.+?)/(.+)/(.+)$");

  public PulsarReaderRouter(CamelContext camelContext, CamelRouteConfiguration camelRouteConfiguration)
  {
    pulsarUri = camelRouteConfiguration.getPulsarRoute();
    kafkaUri = camelRouteConfiguration.getKafkaRoute();

    configureContext(camelContext);
  }

  @Override
  public void configure() throws Exception {

    System.out.println("\t[PULSAR]=> Starting Pulsar Router with URI: " + pulsarUri);
    System.out.println("\t[PULSAR]=> Writing to Kafka with URI: " + kafkaUri);

    // pulsar:persistent://localhost:6650/public/default/outbound-topic
    from("pulsar:" + pulsarUri)
      .log("Message received from Pulsar : ${body}")
      .log("    on the topic ${headers[topic_name]}")
      .log("    published at ${headers[publish_time]}")
      .log("    from the producer ${headers[producer_name]}")
      .setHeader(KafkaConstants.KEY, constant("Pulsar")) // Key of the message
      .setBody(simple("<Pulsar Tag> ${body}")) 
      .to("kafka:" + kafkaUri);
  }

  private void configureContext(CamelContext camelContext)
  {
    Matcher matcher = PATTERN.matcher(pulsarUri);

    if(!matcher.matches()) return; 

    PulsarConfiguration pulsarConfig = new PulsarConfiguration();
    pulsarConfig.setServiceUrl("pulsar://" + matcher.group(2) + "/");
    
    PulsarComponent pulsar = new PulsarComponent(camelContext);
    pulsar.setConfiguration(pulsarConfig);
      
    camelContext.addComponent("pulsar", pulsar);
  }

}
