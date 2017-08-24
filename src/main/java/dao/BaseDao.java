package dao;

import java.util.List;

public interface BaseDao<T> {
    List<T> getAll();

    T getById(int id);

    void deleteById(int id);

    void add(T t);

    void clearTable();
}
