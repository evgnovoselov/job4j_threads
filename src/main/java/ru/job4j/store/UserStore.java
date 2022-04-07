package ru.job4j.store;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class UserStore implements Store {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

    @Override
    public boolean add(User user) {
        return users.putIfAbsent(user.getId(), user) == null;
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            result = true;
        }
        return result;
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
    public Optional<User> findById(int id) {
        return Optional.of(User.of(users.get(id)));
    }

    @Override
    public List<User> findAll() {
        return users.values().stream()
                .map(User::of)
                .toList();
    }
}
