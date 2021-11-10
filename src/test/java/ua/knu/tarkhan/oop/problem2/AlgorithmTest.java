package ua.knu.tarkhan.oop.problem2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlgorithmTest {
    @Test
    void test() {
        extracted(
                List.of(2.0, 1.0),
                List.of(2.0, 1.0),
                List.of(3.0, 4.0, 5.0),
                List.of(9.0, 19.0, 28.0),
                List.of(1.0, 3.0, 5.0));
        extracted(
                List.of(1.0, 1.0),
                List.of(1.0, 2.0),
                List.of(1.0, 3.0, 2.0),
                List.of(1.0, 1.0, 1.0),
                List.of(2.0, -1.0, 1.0));
        extracted(
                List.of(4.0, 5.0),
                List.of(4.0, 5.0),
                List.of(2.0, 1.0, 2.0),
                List.of(18.0, 33.0, 30.0),
                List.of(1.0, 4.0, 5.0));
        extracted(
                List.of(2.0, 4.0),
                List.of(2.0, 4.0),
                List.of(1.0, 2.0, 3.0),
                List.of(11.0, 34.0, 31.0),
                List.of(3.0, 4.0, 5.0));
    }

    private void extracted(List<Double> a, List<Double> b, List<Double> c, List<Double> f, List<Double> expRes) {
        Algorithm al = new Algorithm(a, b, c, f);
        al.start();
        try {
            al.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final List<Double> result = al.getResult();

        assertEquals(expRes.size(), result.size());

        for (int i = 0; i < expRes.size(); i++) {
            assertEquals(expRes.get(i), result.get(i), 0.001);
        }
    }
}
