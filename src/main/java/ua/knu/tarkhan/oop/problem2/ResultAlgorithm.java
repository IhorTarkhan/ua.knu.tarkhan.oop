package ua.knu.tarkhan.oop.problem2;

import lombok.SneakyThrows;

import java.util.concurrent.RecursiveTask;

public class ResultAlgorithm extends RecursiveTask<ResultArgument> {
    private final CoefficientsArgument coefficients;
    private final ResultArgument result;

    public ResultAlgorithm(CoefficientsArgument coefficients, ResultArgument result) {
        this.coefficients = coefficients;
        this.result = result;
    }

    @SneakyThrows
    @Override
    public ResultArgument compute() {
        if (result.index >= 0) {
            result.x.set(result.index, coefficients.alpha.get(result.index) * result.x.get(result.index + 1) + coefficients.beta.get(result.index));
            result.index--;

            ResultAlgorithm target = new ResultAlgorithm(coefficients, result);
            target.fork();
            return target.join();
        }
        return result;
    }

    public ResultArgument getResult() {
        return result;
    }
}
