package ua.knu.tarkhan.oop.problem3;

import lombok.SneakyThrows;

import java.util.Arrays;

public class Problem3 {
    @SneakyThrows
    public static void main(String[] args) {
        Thread subThread = new Thread(() -> {
            try {
                System.out.println("0.1");
                Thread.sleep(100);
                System.out.println("0.2");
            } catch (InterruptedException e) {
                System.out.println("ERROR");
            }
        });

        Data data = new Data(
                new Thread(() -> {
                    try {
                        System.out.println("1.1");
                        Thread.sleep(1000);
                        System.out.println("1.2");
                    } catch (InterruptedException e) {
                        System.out.println("ERROR");
                    }
                }),
                new Thread(() -> {
                    try {
                        System.out.println("2.1");
                        Thread.sleep(100);
                        System.out.println("2.0");

                        subThread.start();
                    } catch (InterruptedException e) {
                        System.out.println("ERROR");
                    }
                }),
                subThread
        );
        for (int i = 0; i < data.getThreads().size() - 1; i++) {
            data.getThreads().get(i).start();
        }
        testMethod(data);
    }

    @SneakyThrows
    public static void testMethod(Data data) {
        while (data.getThreads().stream().anyMatch(Thread::isAlive)) {
            data.getThreads().forEach(t -> {
                System.out.println(t.getName());
                Arrays.stream(t.getStackTrace()).forEach(System.out::println);
            });
            System.out.println("--------------");
            Thread.sleep(10);
        }

    }
}
