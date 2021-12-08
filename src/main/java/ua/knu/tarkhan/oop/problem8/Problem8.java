package ua.knu.tarkhan.oop.problem8;

import java.util.ArrayList;

public class Problem8 {
  public static void main(String[] args) throws InterruptedException {
    MyClass.simulation();
    MyReentrantLock lock = new MyReentrantLock();
    MyClass m = new MyClass();
    ArrayList<Thread> thrs = new ArrayList<>();
    for (int i = 0; i < 5; ++i) {
      thrs.add(
          new Thread(
              () -> {
                for (int j = 0; j < 100000; ++j) {
                  try {
                    lock.lock();
                    m.inc();
                    lock.unlock();
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                }
              }));
      thrs.get(i).start();
    }

    for (int i = 0; i < 5; ++i) {
      thrs.get(i).join();
    }
    System.out.println(m.getData());
  }
}
