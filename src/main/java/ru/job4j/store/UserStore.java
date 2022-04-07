package ru.job4j.store;

public class UserStore implements Store {
    @Override
    public boolean add(User user) {
        return false;
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
