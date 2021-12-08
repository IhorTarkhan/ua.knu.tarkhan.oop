package ua.knu.tarkhan.oop.problem9;

public class Problem9 {
  public static void main(String[] args) {
    MyPhaser ph = new MyPhaser(4);
    ph.arrive();
    ph.register();
    new Thread() {
      public void run() {
        try {
          ph.arriveAndAwait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }.start();

    System.out.println(ph.getArrived());
    ph.arrive();
    System.out.println(ph.getArrived());
    ph.arrive();
    System.out.println(ph.getArrived());
    ph.arriveAndDeregister();
    System.out.println(ph.getArrived());
  }
}
