package ua.knu.tarkhan.oop.problem7;

import java.util.concurrent.BrokenBarrierException;

public class CyclicBarrierCustom {

  private int threadsAmount;
  private int waitingThreads;
  private Runnable cyclicBarrierEvent;
  private boolean isBroken = false;

  public CyclicBarrierCustom(int threadsAmount, Runnable cyclicBarrierEvent) {
    this.threadsAmount = threadsAmount;
    this.waitingThreads = 0;
    this.cyclicBarrierEvent = cyclicBarrierEvent;
  }

  public CyclicBarrierCustom(int threadsAmount) {
    this.threadsAmount = threadsAmount;
    this.waitingThreads = 0;
    this.cyclicBarrierEvent = null;
  }

  public synchronized void await() throws InterruptedException, BrokenBarrierException {
    if (isBroken) throw new BrokenBarrierException();

    waitingThreads++;

    if (waitingThreads != threadsAmount) {
      try {
        this.wait();
      } catch (InterruptedException e) {
        isBroken = true;
        throw e;
      }
    } else {
      waitingThreads = 0;
      notifyAll();
      if (cyclicBarrierEvent != null) cyclicBarrierEvent.run();
    }
  }

  public int getThreadsAmount() {
    return threadsAmount;
  }

  public int getWaitingThreads() {
    return waitingThreads;
  }

  public Runnable getCyclicBarrierEvent() {
    return cyclicBarrierEvent;
  }

  public boolean isBroken() {
    return isBroken;
  }
}
