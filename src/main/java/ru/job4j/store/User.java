package ru.job4j.store;

public class User {
    private final int id;
    private final int amount;

    public User(int id, int amount) {
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
        return new User(user.getId(), user.getAmount());
    }
}
