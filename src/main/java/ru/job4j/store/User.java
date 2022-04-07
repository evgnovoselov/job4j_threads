package ru.job4j.store;

import java.util.Objects;

public class User {
    private final int id;
    private final int amount;

    public User(int id, int amount) {
        if (id <= 0 || amount < 0) {
            throw new IllegalArgumentException("Wrong User args");
        }
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public static User of(User user) {
        return of(user.getId(), user.getAmount());
    }

    public static User of(int id, int amount) {
        return new User(id, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && amount == user.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }
}
