package design.loadbalancer;

import java.util.List;
import java.util.Random;

public class RandomLoadBalancer extends LoadBalancer {

  public RandomLoadBalancer(List<String> ipList) {
    super(ipList);
  }

  @Override
  public String getIp() {
    try {
      Random random = new Random();
      Thread.sleep(1000);
      System.out.println("Inside Random print");
      return ipList.get(random.nextInt(ipList.size()));
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }
}