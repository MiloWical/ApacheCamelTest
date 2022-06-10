package git.milowical;

import org.apache.camel.builder.RouteBuilder;

public class KafkaWriterRouter extends RouteBuilder {

  private String uri;

  public KafkaWriterRouter(CamelRouteConfiguration camelRouteConfiguration)
  {
    uri = camelRouteConfiguration.getKafkaRoute();
  }

  @Override
  public void configure() throws Exception {

    System.out.println("\t[KAFKA]=> Starting Kafka Router with URI: " + uri);

    // kafka:testTopic?brokers=localhost:9092
    from("kafka:" + uri)
        .log("Message received from Kafka : ${body}")
        .log("    on the topic ${headers[kafka.TOPIC]}")
        .log("    on the partition ${headers[kafka.PARTITION]}")
        .log("    with the offset ${headers[kafka.OFFSET]}")
        .log("    with the key ${headers[kafka.KEY]}");
  }

}
