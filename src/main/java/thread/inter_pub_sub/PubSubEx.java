package thread.inter_pub_sub;

public class PubSubEx {

  class Q {
    int num;
    boolean valueSet = false;

    public synchronized void put(int n) {
      while (valueSet) {
        try {
          wait();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      System.out.println("Put :" + n);
      this.num = n;
      valueSet = true;
      notify();
    }

    public synchronized void get() {
      while (!valueSet) {
        try {
          wait();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      System.out.println("Get :"+ num);
      valueSet = false;
      notify();
    }
  }

  class Producer implements Runnable {
    Q q;

    public Producer(Q q) {
      this.q = q;
      new Thread(this, "Producer").start();
    }

    @Override
    public void run() {
      int i = 0;
      while(i < 10) {
        q.put(i++);
        try {
          Thread.sleep(500);
        }
        catch(Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  class Consumer implements Runnable {
    Q q;

    public Consumer(Q q) {
      this.q = q;
      new Thread(this, "Consumer").start();
    }

    @Override
    public void run() {
      while(true) {
        q.get();
        try {
          Thread.sleep(1000);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    PubSubEx pubSub = new PubSubEx();
    Q q = pubSub.new Q();
    pubSub.new Producer(q);
    pubSub.new Consumer(q);

  }

}
