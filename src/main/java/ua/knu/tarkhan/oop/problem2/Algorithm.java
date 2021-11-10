package ua.knu.tarkhan.oop.problem2;

import lombok.SneakyThrows;

import java.util.List;

public class Algorithm {
    @SneakyThrows
    public static List<Double> getResult(List<Double> a, List<Double> b, List<Double> c, List<Double> f) {
        CoefficientsAlgorithm coefficientsAlgorithm = new CoefficientsAlgorithm(a, b, c, f, new CoefficientsArgument(b.get(0) / c.get(0) * (-1), f.get(0) / c.get(0), 1));
        Thread thread1 = new Thread(coefficientsAlgorithm);
        thread1.start();
        thread1.join();
        CoefficientsArgument coefficients = coefficientsAlgorithm.getResult();

        Double temp1 = f.get(f.size() - 1) - a.get(a.size() - 1) * coefficients.beta.get(coefficients.beta.size() - 1);
        Double temp2 = c.get(c.size() - 1) + coefficients.alpha.get(coefficients.alpha.size() - 1) * a.get(a.size() - 1);
        ResultAlgorithm resultAlgorithm = new ResultAlgorithm(coefficients, new ResultArgument(temp1 / temp2, f.size()));
        Thread thread2 = new Thread(resultAlgorithm);
        thread2.start();
        thread2.join();
        ResultArgument result = resultAlgorithm.getResult();

        return result.x;
    }
}
