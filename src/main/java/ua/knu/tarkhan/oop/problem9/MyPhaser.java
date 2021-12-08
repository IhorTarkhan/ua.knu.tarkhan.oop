package ua.knu.tarkhan.oop.problem9;

import java.util.HashMap;
import java.util.Map;

public class MyPhaser {
  private int phase;
  private int parties;
  private int arrived, unArrived;
  Map<Integer, Runnable> onAdvance;

  public MyPhaser(int parties) {
    this.parties = parties;
    this.unArrived = parties;
    this.arrived = 0;
    onAdvance = new HashMap<>();
  }

  public void setOnAdvance(Runnable action, int phase) {
    onAdvance.put(phase, action);
  }

  public int getPhase() {
    return phase;
  }

  public int getParties() {
    return parties;
  }

  public int getArrived() {
    return arrived;
  }

  public int getUnArrived() {
    return unArrived;
  }

  private boolean tryOpen() {
    if (unArrived == 0) {
      if (onAdvance.get(phase) != null) onAdvance.get(phase).run();
      arrived = 0;
      unArrived = parties;
      notifyAll();
      ++phase;
      return true;
    } else {
      return false;
    }
  }

  public synchronized void register() {
    ++parties;
    ++unArrived;
  }

  public synchronized void arrive() {
    ++arrived;
    --unArrived;
    tryOpen();
  }

  public synchronized void arriveAndDeregister() {
    --parties;
    --unArrived;
    tryOpen();
  }

  public synchronized void arriveAndAwait() throws InterruptedException {
    ++arrived;
    --unArrived;
    if (!tryOpen()) {
      this.wait();
    }
  }
}
