package ua.knu.tarkhan.oop.lab1.dao;

import ua.knu.tarkhan.oop.lab1.domain.Subscriber;
import ua.knu.tarkhan.oop.lab1.util.SQLConnector;

import java.util.List;
import java.util.Optional;

public class SubscriberDao implements Dao<Subscriber> {
    private final SQLConnector sqlConnector;

    public SubscriberDao() {
        this.sqlConnector = new SQLConnector();
        createRequiredTable();
    }

    public SubscriberDao(SQLConnector sqlConnector) {
        this.sqlConnector = sqlConnector;
        createRequiredTable();
    }

    private void createRequiredTable() {
        sqlConnector.update(
                """
                        CREATE TABLE IF NOT EXISTS subscriber
                        (
                            id       TEXT PRIMARY KEY DEFAULT (hex(randomblob(16))),
                            tariffId TEXT,
                            name     TEXT
                        );
                        """);
    }

    @Override
    public String save(Subscriber subscriber) {
        return sqlConnector.insert(
                """
                        INSERT INTO subscriber(tariffId, name)
                        VALUES ('%s', '%s');
                        """
                        .formatted(subscriber.tariffId(), subscriber.name()));
    }

    @Override
    public List<Subscriber> findAll() {
        return sqlConnector.select(
                "SELECT tariffId, name FROM subscriber;",
                result -> new Subscriber(result.getString("tariffId"), result.getString("name")));
    }

    @Override
    public Optional<Subscriber> findById(String id) {
        List<Subscriber> selected = sqlConnector.select(
                "SELECT tariffId, name FROM subscriber WHERE id = %s;".formatted(id),
                result -> new Subscriber(result.getString("tariffId"), result.getString("name")));
        if (selected.size() == 1) {
            return Optional.of(selected.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(String id) {
        sqlConnector.update("DELETE FROM subscriber WHERE id = '%s'".formatted(id));
    }
}
