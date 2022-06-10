package git.milowical;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;

public class KafkaWriterRouter extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    // from("timer:trigger?period=1000")
    // .setBody(constant("Message from Camel")) // Message to send
    // .setHeader(KafkaConstants.KEY, constant("Camel")) // Key of the message
    // .to("kafka:testTopic?brokers=localhost:9092");

    from("kafka:testTopic?brokers=localhost:9092")
        .log("Message received from Kafka : ${body}")
        .log("    on the topic ${headers[kafka.TOPIC]}")
        .log("    on the partition ${headers[kafka.PARTITION]}")
        .log("    with the offset ${headers[kafka.OFFSET]}")
        .log("    with the key ${headers[kafka.KEY]}");
  }

}
