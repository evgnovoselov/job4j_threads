package ru.job4j.nonblock.cache;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class CacheTest {
    /**
     * TODO Добавить комментарий и переименовать метод.
     */
    @Test
    public void add() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        base.setName("Base");
        cache.add(base);
        Base user1 = new Base(1, 0);
//        Base user1 = base;
        user1.setName("User 1");
        System.out.println(cache.update(user1));
        assertTrue(true);
    }
}