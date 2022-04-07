package ru.job4j.store;

import java.util.concurrent.ConcurrentHashMap;

public class UserStore implements Store {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

    @Override
    public boolean add(User user) {
        return users.putIfAbsent(user.getId(), user) == null;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public boolean transfer(int fromId, int toId, int amount) {
        return false;
    }

    @Override
    public User findById(int id) {
        return null;
    }
}
