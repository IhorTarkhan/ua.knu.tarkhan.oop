package ua.knu.tarkhan.oop.lab1.dao.tariff;

import ua.knu.tarkhan.oop.lab1.domain.tariff.ContractTariff;
import ua.knu.tarkhan.oop.lab1.domain.tariff.Tariff;
import ua.knu.tarkhan.oop.lab1.util.SQLConnector;

import java.util.List;
import java.util.Optional;

public class ContractTariffDao implements TariffDaoInterface {
    private final SQLConnector sqlConnector;

    public ContractTariffDao() {
        this.sqlConnector = new SQLConnector();
        createRequiredTable();
    }

    public ContractTariffDao(SQLConnector sqlConnector) {
        this.sqlConnector = sqlConnector;
        createRequiredTable();
    }

    private void createRequiredTable() {
        sqlConnector.update("""
                CREATE TABLE IF NOT EXISTS contract_tariff
                (
                    id              TEXT PRIMARY KEY DEFAULT (hex(randomblob(16))),
                    name            TEXT,
                    subscriptionFee NUMERIC,
                    availableCredit NUMERIC
                );
                """);
    }

    @Override
    public String save(Tariff tariff) {
        ContractTariff object = (ContractTariff) tariff;
        return sqlConnector.insert(
                """
                        INSERT INTO contract_tariff(name, subscriptionFee, availableCredit)
                        VALUES ('%s', %s, %s);
                        """
                        .formatted(object.getName(), object.getSubscriptionFee(), object.getAvailableCredit()));
    }

    @Override
    public List<Tariff> findAll() {
        return sqlConnector.select(
                "SELECT name, subscriptionFee, availableCredit FROM contract_tariff;",
                result ->
                        new ContractTariff(
                                result.getString("name"),
                                result.getBigDecimal("subscriptionFee"),
                                result.getBigDecimal("availableCredit")));
    }

    @Override
    public Optional<Tariff> findById(String id) {
        List<ContractTariff> selected = sqlConnector.select(
                "SELECT name, subscriptionFee, availableCredit FROM contract_tariff;",
                result ->
                        new ContractTariff(
                                result.getString("name"),
                                result.getBigDecimal("subscriptionFee"),
                                result.getBigDecimal("availableCredit")));
        if (selected.size() == 1) {
            return Optional.of(selected.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(String id) {
        sqlConnector.update("DELETE FROM contract_tariff WHERE id = '%s'".formatted(id));
    }
}
