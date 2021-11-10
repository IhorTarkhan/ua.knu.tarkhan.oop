package ua.knu.tarkhan.oop.problem3;

import java.util.List;

public class Data {
    private List<Thread> threads;

    public Data(Thread... threads) {
        this.threads = List.of(threads);
    }

    public List<Thread> getThreads() {
        return threads;
    }
}
