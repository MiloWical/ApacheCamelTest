package git.milowical;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.kafka.KafkaConfiguration;
import org.apache.camel.component.pulsar.PulsarComponent;
import org.apache.camel.component.pulsar.PulsarConfiguration;
import org.apache.camel.impl.*;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    // System.out.println( "Hello World!" );

    CamelContext context = new DefaultCamelContext();

    try {
      // PulsarConfiguration pulsarConfig = new PulsarConfiguration();
      // pulsarConfig.setServiceUrl("pulsar://localhost:6650/");
      // // //pulsarConfig.setSubscriptionName("inbound-sub");
      // PulsarComponent pulsar = new PulsarComponent(context);
      // // pulsar.setConfiguration(pulsarConfig);
      // context.addComponent("pulsar", pulsar);
      // context.addRoutes(new HelloWorldRouter());
      context.addRoutes(new KafkaWriterRouter());
      context.addRoutes(new PulsarReaderRouter(context));

      context.start();

      System.in.read();
    } catch (Exception e) {
      System.out.println(e);
      printStackTrace(e);
    }

    context.stop();

    // context.start();

    // try {
    // CamelContext context = new DefaultCamelContext();

    // KafkaConfiguration kafkaConfig = new KafkaConfiguration();
    // kafkaConfig.setBrokers("localhost:9092");

    // KafkaComponent kafka = new KafkaComponent();
    // kafka.setConfiguration(kafkaConfig);
    // //kafka.brokers("kafka:9092"); // kafka1, kafka2,Register 3 servers of kafka3
    // as brokers
    // context.addComponent("kafka", kafka);

    // context.addRoutes(new RouteBuilder() {
    // public void configure() {
    // from("timer:trigger?period=1000") //Run every 1000 milliseconds
    // .routeId("kafka_producer_route")
    // .setBody(simple("${date:now:yyyy-MM-dd HH:mm:ss}")) //Set the current date
    // and time for the BODY of the message
    // .to("kafka:test01"); //Publish message to topic test01

    // from("kafka:test01") //Subscribe message from topic test01
    // .routeId("kafka_consumer_route")
    // .log("body = ${body}"); //Display the BODY content of the message in the log
    // }
    // });

    // context.start();

    // System.in.read();

    // context.stop();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
  }

  public static void printStackTrace(Throwable t) {
    System.out.println(t);
    for (StackTraceElement ste : t.getStackTrace()) {
      System.out.println("\tat " + ste);
    }
    Throwable cause = t.getCause();
    if (cause != null) {
      System.out.print("Caused by ");
      printStackTrace(cause);
    }
  }
}
