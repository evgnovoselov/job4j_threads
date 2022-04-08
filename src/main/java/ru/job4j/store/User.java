package ru.job4j.store;

import java.util.Objects;

public class User {
    private final int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        setAmount(amount);
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static User of(User user) {
        return new User(user.getId(), user.getAmount());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
