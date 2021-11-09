package ua.knu.tarkhan.oop.lab1.util;

import ua.knu.tarkhan.oop.Path;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLConnector {
    public static final String DB_URL = "jdbc:sqlite:" + Path.ROOT + "lab1/sample.db";

    public SQLConnector() {
        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> select(String sql, FunctionWithException<ResultSet, T> parser) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            var result = new ArrayList<T>();
            while (rs.next()) {
                result.add(parser.apply(rs));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void update(String sql) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String insert(String sql) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("No rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getString(1);
                } else {
                    throw new RuntimeException("No ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface FunctionWithException<T, R> {
        R apply(T t) throws SQLException;
    }
}
