package ru.job4j.store;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Objects;

@ThreadSafe
public class User {
    @GuardedBy("this")
    private final int id;
    @GuardedBy("this")
    private final int amount;

    public User(int id, int amount) {
        if (id <= 0 || amount < 0) {
            throw new IllegalArgumentException("Wrong User args");
        }
        this.id = id;
        this.amount = amount;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized int getAmount() {
        return amount;
    }

    public static synchronized User of(User user) {
        return of(user.getId(), user.getAmount());
    }

    public static synchronized User of(int id, int amount) {
        return new User(id, amount);
    }

    @Override
    public synchronized boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && amount == user.amount;
    }

    @Override
    public synchronized int hashCode() {
        return Objects.hash(id, amount);
    }
}
