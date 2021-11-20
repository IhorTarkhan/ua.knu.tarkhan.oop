package ua.knu.tarkhan.oop.problem10;

public class Problem10 {
    public static void main(String[] args) throws Exception {

        ThreadPool threadPool = new ThreadPool(3, 10);

        for (int i = 0; i < 10; i++) {

            int taskNo = i;
            threadPool.execute(() -> {
                String message =
                        Thread.currentThread().getName()
                                + ": Task " + taskNo;
                System.out.println(message);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                System.out.println("Finish: " + message);
            });
        }

        threadPool.waitUntilAllTasksFinished();
        threadPool.stop();

    }

}
