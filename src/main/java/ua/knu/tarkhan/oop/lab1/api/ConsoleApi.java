package ua.knu.tarkhan.oop.lab1.api;

import ua.knu.tarkhan.oop.lab1.dao.SubscriberDao;
import ua.knu.tarkhan.oop.lab1.dao.tariff.ContractTariffDao;
import ua.knu.tarkhan.oop.lab1.dao.tariff.PrepayTariffDao;
import ua.knu.tarkhan.oop.lab1.dao.tariff.TariffDao;
import ua.knu.tarkhan.oop.lab1.domain.tariff.ContractTariff;
import ua.knu.tarkhan.oop.lab1.domain.tariff.PrepayTariff;
import ua.knu.tarkhan.oop.lab1.util.SQLConnector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ConsoleApi {
    private final SQLConnector sqlConnector = new SQLConnector();
    private final SubscriberDao subscriberDao = new SubscriberDao();
    private final TariffDao tariffDao = new TariffDao();
    private final ContractTariffDao contractTariffDao = new ContractTariffDao();
    private final PrepayTariffDao prepayTariffDao = new PrepayTariffDao();

    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("""
                Supported operations:
                upload-demo
                show-tariffs
                show-contract-tariffs
                show-prepay-tariffs
                show-subscribers
                exit""");
        boolean isReturn = true;
        while (isReturn) {
            var commands = in.nextLine().split(" ");
            switch (commands[0]) {
                case "upload-demo" -> handAllExceptions(this::uploadDemo);
                case "show-tariffs" -> handAllExceptions(this::showTariffs);
                case "show-contract-tariffs" -> handAllExceptions(this::showContractTariffs);
                case "show-prepay-tariffs" -> handAllExceptions(this::showPrepayTariffs);
                case "show-subscribers" -> handAllExceptions(this::showSubscriber);
                case "exit" -> isReturn = false;
                default -> System.out.println("unsupported operation");
            }
        }
    }

    private void handAllExceptions(RunnableThrowable runnableThrowable) {
        try {
            runnableThrowable.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uploadDemo() throws IOException {
        Files.readAllLines(Path.of("demo_data", "mock_data.sql"))
                .stream()
                .filter(it -> !it.isBlank())
                .forEach(sqlConnector::insert);
        System.out.println("demo data uploaded well");
    }

    private void showSubscriber() {
        var subscribers = subscriberDao.findAll();
        if (subscribers.isEmpty()) {
            System.out.println("result is empty");
        } else {
            System.out.println("name\t|\ttariffId");
            subscribers.forEach(it -> System.out.printf("%s\t|\t%s%n", it.name(), it.tariffId()));
        }
    }

    private void showTariffs() {
        var subscribers = tariffDao.findAll();
        if (subscribers.isEmpty()) {
            System.out.println("result is empty");
        } else {
            System.out.println("name\t|\tsubscription fee");
            subscribers.forEach(it -> System.out.printf("%s\t|\t%s%n", it.getName(), it.getSubscriptionFee()));
        }
    }

    private void showContractTariffs() {
        var subscribers = contractTariffDao.findAll();
        if (subscribers.isEmpty()) {
            System.out.println("result is empty");
        } else {
            System.out.println("name\t|\tsubscription fee\t|\tavailable credit");
            subscribers.stream()
                    .map(it -> (ContractTariff) it)
                    .forEach(it -> System.out.printf("%s\t|\t%s\t|\t%s%n", it.getName(), it.getSubscriptionFee(), it.getAvailableCredit()));
        }
    }

    private void showPrepayTariffs() {
        var subscribers = prepayTariffDao.findAll();
        if (subscribers.isEmpty()) {
            System.out.println("result is empty");
        } else {
            System.out.println("name\t|\tsubscription fee\t|\tavailable credit");
            subscribers.stream()
                    .map(it -> (PrepayTariff) it)
                    .forEach(it -> System.out.printf("%s\t|\t%s\t|\t%s%n", it.getName(), it.getSubscriptionFee(), it.getPrepay()));
        }
    }

    private interface RunnableThrowable {
        void accept() throws Exception;
    }
}
