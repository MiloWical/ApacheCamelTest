package git.milowical;

public class CamelRouteConfiguration {
  
  private String pulsarRoute;
  private String kafkaRoute;
  

  public CamelRouteConfiguration(String pulsarRoute, String kafkaRoute)
  {
    this.pulsarRoute = pulsarRoute;
    this.kafkaRoute = kafkaRoute;
  }

  public String getPulsarRoute()
  {
    return pulsarRoute;
  }

  public String getKafkaRoute()
  {
    return kafkaRoute;
  }
}
