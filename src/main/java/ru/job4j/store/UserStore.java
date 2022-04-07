package ru.job4j.store;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class UserStore implements Store {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

    @Override
    public boolean add(User user) {
        User copy = User.of(user);
        return users.putIfAbsent(copy.getId(), copy) == null;
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        User copy = User.of(user);
        if (users.containsKey(copy.getId())) {
            users.put(copy.getId(), copy);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    @Override
    public boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User from = users.get(fromId);
        User to = users.get(toId);
        if (from != null && to != null && from.getAmount() > amount) {
            User fromTransferred = User.of(from.getId(), from.getAmount() - amount);
            User toTransferred = User.of(to.getId(), to.getAmount() + amount);
            update(fromTransferred);
            update(toTransferred);
            result = true;
        }
        return result;
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
