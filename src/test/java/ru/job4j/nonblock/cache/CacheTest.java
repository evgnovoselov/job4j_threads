package ru.job4j.nonblock.cache;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CacheTest {
    /**
     * TODO Добавить комментарий и переименовать метод.
     */
    @Test
    public void add() {
        Map<Integer, Base> map = new HashMap<>();
        Base base = new Base(1, 0);
        map.put(base.getId(), base);
        Base user1 = map.get(base.getId());
        user1.setName("User 1");
        Base user2 = map.get(base.getId());
        user2.setName("User 2");
        map.put(user1.getId(), user1);
        map.put(user2.getId(), user2);
        assertEquals(Map.of(), map);
    }
}