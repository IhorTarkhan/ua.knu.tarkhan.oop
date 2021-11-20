package ua.knu.tarkhan.oop.problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmReverse extends Thread {
    private final List<Double> a;
    private final List<Double> b;
    private final List<Double> c;
    private final List<Double> f;

    private final List<Double> alpha;
    private final List<Double> beta;

    private final List<Double> x;
    private final int start;
    private final int finish;

    public AlgorithmReverse(List<Double> a, List<Double> b, List<Double> c,
                            List<Double> f, List<Double> res, int start, int finish) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.f = f;
        this.x = res;
        this.start = start;
        this.finish = finish;
        this.alpha = new ArrayList<>(Collections.nCopies(b.size() - 1, 0.0));
        this.beta = new ArrayList<>(Collections.nCopies(b.size() - 1, 0.0));
    }

    private void calculateCoefficients() {
        alpha.add(a.get(a.size() - 1) * (-1) / c.get(c.size() - 1));
        beta.add(f.get(f.size() - 1) / c.get(c.size() - 1));
        for (int i = b.size() - 1; i > 0; i--) {
            alpha.set(i - 1, (-1) * a.get(i - 1) / (b.get(i) * alpha.get(i) + c.get(i)));
            beta.set(i - 1, (f.get(i) - b.get(i) * beta.get(i)) / (b.get(i) * alpha.get(i) + c.get(i)));
        }
    }

    @Override
    public void run() {
        calculateCoefficients();
        Double temp1 = (f.get(0) - b.get(0) * beta.get(0));
        Double temp2 = (b.get(0) * alpha.get(0) + c.get(0));
        x.set(start, temp1 / temp2);
        for (int i = start + 1; i < finish; i++) {
            x.set(i, (x.get(i - 1) * alpha.get(i - 1)) + beta.get(i - 1));
        }
    }

    public List<Double> getResult() {
        return x;
    }
}
