package ua.knu.tarkhan.oop.lab1;

import ua.knu.tarkhan.oop.lab1.api.ConsoleApi;

public class Application {
    private static final ConsoleApi consoleApi = new ConsoleApi();

    public static void main(String[] args) {
        consoleApi.run();
    }
}
