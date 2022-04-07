package ru.job4j.store;

public interface Store {
    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);
}
