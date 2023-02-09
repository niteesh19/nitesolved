package design.loadbalancer;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WeightedRoundRobinLoadBalancer extends RoundRobinLoadBalancer {

  public WeightedRoundRobinLoadBalancer(Map<String, Integer> ipMap) {
    super(
        ipMap.keySet()
            .stream()
            .map(ip -> {
              List<String> tempList =  new LinkedList<>();
              for (int i=0; i<ipMap.get(ip); i++) {
                tempList.add(ip);
              }
              return tempList;
            })
            .flatMap(Collection::stream)
            .collect(Collectors.toList())
    );
  }
}
/*
public class WeightRoundRobin implements LoadBalance {
    private static Integer position = 0;
    @Override
    public String getServer(String clientIp) {
        Set<String> servers = IpPool.ipMap.keySet();
        List<String> serverList = new ArrayList<>();
        Iterator<String> iterator = servers.iterator();
        while (iterator.hasNext()) {
            String serverItem = iterator.next();
            Integer weight = IpPool.ipMap.get(serverItem);
            if (weight > 0) {
                for (int i = 0; i < weight; i++) {
                    serverList.add(serverItem);
                }
            }
        }
        synchronized (position) {
            if (position > serverList.size()) {
                position = 0;
            }

            String target = serverList.get(position);
            position++;
            return target;
        }
    }
}
 */
