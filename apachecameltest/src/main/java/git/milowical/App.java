package git.milowical;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class App {
  public static void main(String[] args) {
    
    CamelRouteConfiguration camelRouteConfiguration = new CamelRouteConfiguration(args[0], args[1]);

    CamelContext context = new DefaultCamelContext();

    try {
      context.addRoutes(new KafkaWriterRouter(camelRouteConfiguration));
      context.addRoutes(new PulsarReaderRouter(context, camelRouteConfiguration));

      context.start();

      System.in.read();
    } 
    catch (Exception e) {
      System.out.println(e);
      Util.printStackTrace(e); // Use this to get the full stack trace, not just a summarization.
    }

    context.stop();
  }

  
}
