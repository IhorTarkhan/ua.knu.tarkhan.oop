package ua.knu.tarkhan.oop.lab1.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    String save(T t);

    List<T> findAll();

    Optional<T> findById(String id);

    void deleteById(String id);
}
