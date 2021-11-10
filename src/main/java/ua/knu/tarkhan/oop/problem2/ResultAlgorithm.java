package ua.knu.tarkhan.oop.problem2;

import lombok.SneakyThrows;

public class ResultAlgorithm implements Runnable {
    private final CoefficientsArgument coefficients;
    private ResultArgument result;

    public ResultAlgorithm(CoefficientsArgument coefficients, ResultArgument result) {
        this.coefficients = coefficients;
        this.result = result;
    }

    @SneakyThrows
    @Override
    public void run() {
        if (result.index >= 0) {
            result.x.set(result.index, coefficients.alpha.get(result.index) * result.x.get(result.index + 1) + coefficients.beta.get(result.index));
            result.index--;

            ResultAlgorithm target = new ResultAlgorithm(coefficients, result);
            Thread thread = new Thread(target);
            thread.start();
            thread.join();
            result = target.result;
        }
    }

    public ResultArgument getResult() {
        return result;
    }
}
