package ru.job4j.store;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Потокобезопасное хранилище.
 */
@ThreadSafe
public final class UserStore implements Store {
    @GuardedBy("this")
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

    @Override
    public synchronized boolean add(User user) {
        return users.putIfAbsent(user.getId(), user) == null;
    }

    @Override
    public synchronized boolean update(User user) {
        return users.replace(user.getId(), user) != null;
    }

    @Override
    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User from = users.get(fromId);
        User to = users.get(toId);
        if (from != null && to != null && from.getAmount() >= amount) {
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
            result = true;
        }
        return result;
    }

    @Override
    public synchronized Optional<User> findById(int id) {
        return Optional.of(User.of(users.get(id)));
    }

    @Override
    public synchronized List<User> findAll() {
        return users.values().stream()
                .map(User::of)
                .toList();
    }
}
