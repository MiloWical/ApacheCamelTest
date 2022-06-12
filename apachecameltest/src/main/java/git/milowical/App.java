package git.milowical;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class App {
  public static void main(String[] args) {

    CamelRouteConfiguration camelRouteConfiguration = new CamelRouteConfiguration();
    camelRouteConfiguration.setPulsarRoute(args[0]);
    camelRouteConfiguration.setKafkaRoute(args[1]);

    CamelContext context = new DefaultCamelContext();

    setupShutdownHook(context);

    try {
      context.addRoutes(new KafkaWriterRouter(camelRouteConfiguration));
      context.addRoutes(new PulsarReaderRouter(context, camelRouteConfiguration));

      context.start();
    } catch (Exception e) {
      System.out.println(e);
      Util.printStackTrace(e); // Use this to get the full stack trace, not just a summarization.
    }
  }

  private static void setupShutdownHook(CamelContext context) {
    Thread shutdownListener = new Thread() {
      public void run() {
        try {
          System.out.println("><><>< CLOSING CAMEL CONTEXT ><><><");
          context.close();
        } catch (Exception e) {
          System.out.println(e);
          Util.printStackTrace(e); // Use this to get the full stack trace, not just a summarization.
        }
      }
    };

    Runtime.getRuntime().addShutdownHook(shutdownListener);

    // No-op to keep a thread running until SIGTERM
    Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {}, 0, 2,
        TimeUnit.SECONDS);

  }
}
