package ua.knu.tarkhan.oop.problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm extends Thread {
    private final List<Double> a;
    private final List<Double> b;
    private final List<Double> c;
    private final List<Double> f;

    private final List<Double> alpha = new ArrayList<>();
    private final List<Double> beta = new ArrayList<>();
    private final List<Double> z = new ArrayList<>();

    private final List<Double> x;

    public Algorithm(List<Double> a, List<Double> b, List<Double> c, List<Double> f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.f = f;
        this.x = new ArrayList<>(Collections.nCopies(f.size(), 0.0));
    }

    private void calculateCoefficients() {
        alpha.add(b.get(0) / c.get(0) * (-1));
        beta.add(f.get(0) / c.get(0));
        for (int i = 1; i < b.size(); i++) {
            z.add(c.get(i) + alpha.get(alpha.size() - 1) * a.get(i - 1));
            alpha.add((-1) * b.get(i) / z.get(z.size() - 1));
            beta.add((f.get(i) - a.get(i - 1) * beta.get(beta.size() - 1)) / z.get(z.size() - 1));
        }
    }

    public void run() {
        calculateCoefficients();
        Double temp1 = (f.get(f.size() - 1) - a.get(a.size() - 1) * beta.get(beta.size() - 1));
        Double temp2 = c.get(c.size() - 1) + alpha.get(alpha.size() - 1) * a.get(a.size() - 1);
        x.set(x.size() - 1, temp1 / temp2);
        for (int i = x.size() - 2; i >= 0; i--) {
            x.set(i, alpha.get(i) * x.get(i + 1) + beta.get(i));
        }
    }

    public List<Double> getResult() {
        return x;
    }
}
