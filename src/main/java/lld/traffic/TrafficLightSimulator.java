package lld.traffic;


public class TrafficLightSimulator {

  public static void main(String[] args) {
    boolean  condition = false;
    MyLock lock = new MyLock(condition);
    Thread threadOne = new Thread(new TrafficLightWorker(lock, 5), "One");
    Thread threadTwo = new Thread(new TrafficLightWorker(lock, 4), "Two");

    threadOne.start();
    threadTwo.start();

  }
}