package ua.knu.tarkhan.oop.problem2;

import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class CoefficientsAlgorithm extends RecursiveTask<CoefficientsArgument> {
    private final List<Double> a;
    private final List<Double> b;
    private final List<Double> c;
    private final List<Double> f;
    private final CoefficientsArgument coefficients;

    public CoefficientsAlgorithm(List<Double> a, List<Double> b, List<Double> c, List<Double> f, CoefficientsArgument coefficients) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.f = f;
        this.coefficients = coefficients;
    }

    @SneakyThrows
    @Override
    public CoefficientsArgument compute() {
        if (coefficients.index < b.size()) {
            Double lastAlpha = coefficients.alpha.get(coefficients.alpha.size() - 1);
            Double lastBeta = coefficients.beta.get(coefficients.beta.size() - 1);
            double temp = c.get(coefficients.index) + lastAlpha * a.get(coefficients.index - 1);
            coefficients.alpha.add((-1) * b.get(coefficients.index) / temp);
            coefficients.beta.add((f.get(coefficients.index) - a.get(coefficients.index - 1) * lastBeta) / temp);
            coefficients.index++;

            CoefficientsAlgorithm target = new CoefficientsAlgorithm(a, b, c, f, coefficients);
            target.fork();
            return target.join();
        }
        return coefficients;
    }
}
