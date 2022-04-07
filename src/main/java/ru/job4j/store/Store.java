package ru.job4j.store;

import java.util.Optional;

public interface Store {
    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);

    boolean transfer(int fromId, int toId, int amount);

    Optional<User> findById(int id);
}
