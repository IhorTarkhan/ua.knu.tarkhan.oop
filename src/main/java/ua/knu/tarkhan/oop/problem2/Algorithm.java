package ua.knu.tarkhan.oop.problem2;

import java.util.List;

public class Algorithm extends Thread {
    private final List<Double> a;
    private final List<Double> b;
    private final List<Double> c;
    private final List<Double> f;

    private List<Double> x;

    public Algorithm(List<Double> a, List<Double> b, List<Double> c, List<Double> f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.f = f;
    }

    public void run() {
        CoefficientsArgument coefficients = new CoefficientsArgument(b.get(0) / c.get(0) * (-1), f.get(0) / c.get(0), 1);
        calculateCoefficients(coefficients);

        Double temp1 = f.get(f.size() - 1) - a.get(a.size() - 1) * coefficients.beta.get(coefficients.beta.size() - 1);
        Double temp2 = c.get(c.size() - 1) + coefficients.alpha.get(coefficients.alpha.size() - 1) * a.get(a.size() - 1);
        ResultArgument result = new ResultArgument(temp1 / temp2, f.size());
        calculateResult(coefficients, result);
        this.x = result.x;
    }

    private void calculateResult(CoefficientsArgument coefficients, ResultArgument result) {
        for (int i = result.x.size() - 2; i >= 0; i--) {
            result.x.set(i, coefficients.alpha.get(i) * result.x.get(i + 1) + coefficients.beta.get(i));
        }
    }

    private void calculateCoefficients(CoefficientsArgument coefficients) {
        if (coefficients.index < b.size()) {
            Double lastAlpha = coefficients.alpha.get(coefficients.alpha.size() - 1);
            Double lastBeta = coefficients.beta.get(coefficients.beta.size() - 1);
            double temp = c.get(coefficients.index) + lastAlpha * a.get(coefficients.index - 1);
            coefficients.alpha.add((-1) * b.get(coefficients.index) / temp);
            coefficients.beta.add((f.get(coefficients.index) - a.get(coefficients.index - 1) * lastBeta) / temp);
            coefficients.index++;
            calculateCoefficients(coefficients);
        }
    }

    public List<Double> getResult() {
        return x;
    }
}
