package ua.knu.tarkhan.oop.problem10;

import java.util.concurrent.BlockingQueue;

public class PoolThreadRunnable implements Runnable {

    private final BlockingQueue<Runnable> taskQueue;
    private Thread thread;
    private boolean isStopped = false;

    public PoolThreadRunnable(BlockingQueue<Runnable> queue) {
        taskQueue = queue;
    }

    public void run() {
        this.thread = Thread.currentThread();
        while (!isStopped()) {
            try {
                Runnable runnable = taskQueue.take();
                runnable.run();
            } catch (Exception ignored) {
            }
        }
    }

    public synchronized void doStop() {
        isStopped = true;
        this.thread.interrupt();
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}
