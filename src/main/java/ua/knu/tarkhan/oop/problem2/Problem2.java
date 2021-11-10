package ua.knu.tarkhan.oop.problem2;

import java.util.List;

public class Problem2 {
    public static void main(String[] args) {
        List<Double> a = List.of(1.0, 1.0);
        List<Double> b = List.of(1.0, 2.0);
        List<Double> c = List.of(1.0, 3.0, 2.0);
        List<Double> f = List.of(1.0, 3.0, 2.0);

        System.out.println(Algorithm.getResult(a, b, c, f));
    }
}
