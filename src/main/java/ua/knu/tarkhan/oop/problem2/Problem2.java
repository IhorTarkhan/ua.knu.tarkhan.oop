package ua.knu.tarkhan.oop.problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem2 {
    public static void main(String[] args) {
        List<Double> a = new ArrayList<>();
        a.add(1.0);
        List<Double> b = new ArrayList<>();
        b.add(1.0);
        List<Double> c = new ArrayList<>();
        c.add(1.0);
        c.add(-1.0);
        List<Double> f = new ArrayList<>();
        f.add(-2.0);
        f.add(0.0);
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
