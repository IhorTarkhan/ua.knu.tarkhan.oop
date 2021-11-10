package ua.knu.tarkhan.oop.problem2;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ResultArgument {
    List<Double> x;
    int index;

    public ResultArgument(Double lastValue, int size) {
        this.index = size - 2;
        this.x = new CopyOnWriteArrayList<>(Collections.nCopies(size, 0.0));
        this.x.set(size - 1, lastValue);
    }
}
