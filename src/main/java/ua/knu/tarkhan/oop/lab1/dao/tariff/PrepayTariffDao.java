package ua.knu.tarkhan.oop.lab1.dao.tariff;

import ua.knu.tarkhan.oop.lab1.domain.tariff.PrepayTariff;
import ua.knu.tarkhan.oop.lab1.domain.tariff.Tariff;
import ua.knu.tarkhan.oop.lab1.util.SQLConnector;

import java.util.List;
import java.util.Optional;

public class PrepayTariffDao implements TariffDaoInterface {
    private final SQLConnector sqlConnector;

    public PrepayTariffDao() {
        this.sqlConnector = new SQLConnector();
        createRequiredTable();
    }

    public PrepayTariffDao(SQLConnector sqlConnector) {
        this.sqlConnector = sqlConnector;
        createRequiredTable();
    }

    private void createRequiredTable() {
        sqlConnector.update(
                """
                        CREATE TABLE IF NOT EXISTS prepay_tariff
                        (
                            id              TEXT PRIMARY KEY DEFAULT (hex(randomblob(16))),
                            name            TEXT,
                            subscriptionFee NUMERIC,
                            prepay          NUMERIC
                        );
                        """);
    }

    @Override
    public String save(Tariff tariff) {
        PrepayTariff object = (PrepayTariff) tariff;
        return sqlConnector.insert(
                """
                        INSERT INTO prepay_tariff(name, subscriptionFee, prepay)
                        VALUES ('%s', %s, %s);
                        """
                        .formatted(object.getName(), object.getSubscriptionFee(), object.getPrepay())
        );
    }

    @Override
    public List<Tariff> findAll() {
        return sqlConnector.select(
                "SELECT name, subscriptionFee, prepay FROM prepay_tariff;",
                result ->
                        new PrepayTariff(
                                result.getString("name"),
                                result.getBigDecimal("subscriptionFee"),
                                result.getBigDecimal("prepay")));
    }

    @Override
    public Optional<Tariff> findById(String id) {
        List<PrepayTariff> selected = sqlConnector.select(
                "SELECT name, subscriptionFee, prepay FROM prepay_tariff;",
                result ->
                        new PrepayTariff(
                                result.getString("name"),
                                result.getBigDecimal("subscriptionFee"),
                                result.getBigDecimal("prepay")));
        if (selected.size() == 1) {
            return Optional.of(selected.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(String id) {
        sqlConnector.update("DELETE FROM prepay_tariff WHERE id = '%s'".formatted(id));
    }
}
