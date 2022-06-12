package git.milowical;

public class CamelRouteConfiguration {
  
  private String pulsarRoute;
  private String kafkaRoute;

  public void setPulsarRoute(String pulsarRoute)
  {
    this.pulsarRoute = pulsarRoute;
  }

  public String getPulsarRoute()
  {
    return pulsarRoute;
  }

  public void setKafkaRoute(String kafkaRoute)
  {
    this.kafkaRoute = kafkaRoute;
  }

  public String getKafkaRoute()
  {
    return kafkaRoute;
  }
}
