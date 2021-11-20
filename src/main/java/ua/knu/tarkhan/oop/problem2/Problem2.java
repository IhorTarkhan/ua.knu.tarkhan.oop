package ua.knu.tarkhan.oop.problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem2 {
    public static void main(String[] args) {
        List<Double> a = List.of(1.0);
        List<Double> b = List.of(1.0);
        List<Double> c = List.of(1.0, -1.0);
        List<Double> f = List.of(-2.0, 0.0);

        List<Double> result = new ArrayList<>(Collections.nCopies(f.size(), 0.0));

        Algorithm al = new Algorithm(a, b, c, f, result, 0, result.size() / 2);
        AlgorithmReverse al2 = new AlgorithmReverse(a, b, c, f, result, result.size() / 2, result.size());

        al.start();
        al2.start();

        try {
            al.join();
            al2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }
}
