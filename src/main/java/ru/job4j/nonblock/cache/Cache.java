package ru.job4j.nonblock.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return false;
    }

    public boolean update(Base model) {
        return false;
    }

    public void delete(Base model) {
    }
}
