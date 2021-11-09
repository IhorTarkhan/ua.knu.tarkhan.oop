package ua.knu.tarkhan.oop.problem2;

import java.util.List;

public class Problem2 {
    public static void main(String[] args) {
        List<Double> a = List.of(1.0, 1.0);
        List<Double> b = List.of(1.0, 2.0);
        List<Double> c = List.of(1.0, 3.0, 2.0);
        List<Double> f = List.of(1.0, 3.0, 2.0);

        Algorithm al = new Algorithm(a, b, c, f);

        al.start();

        try {
            al.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(al.getResult());
    }
}
