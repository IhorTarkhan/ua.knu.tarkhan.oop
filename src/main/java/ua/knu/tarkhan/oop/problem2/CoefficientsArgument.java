package ua.knu.tarkhan.oop.problem2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CoefficientsArgument {
    List<Double> alpha = new CopyOnWriteArrayList<>();
    List<Double> beta = new CopyOnWriteArrayList<>();
    int index;

    public CoefficientsArgument(Double firstAlpha, Double firstBeta, int index) {
        this.index = index;
        this.alpha.add(firstAlpha);
        this.beta.add(firstBeta);
    }
}
