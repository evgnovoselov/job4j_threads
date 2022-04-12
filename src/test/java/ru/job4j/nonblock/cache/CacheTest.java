package ru.job4j.nonblock.cache;

import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {
    /**
     * Тестирование добавления объекта в кеш.
     */
    @Test
    public void whenAddThenHaveThis() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        base.setName("Base");
        assertTrue(cache.add(base));
        Base expected = new Base(1, 0);
        expected.setName("Base");
        Base actual = cache.get(1);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getVersion(), actual.getVersion());
        assertEquals(expected.getName(), actual.getName());
    }

    /**
     * Проверка инкримента при обновлении объекта.
     */
    @Test
    public void whenUpdateThenUpdateAndIncrementVersion() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        base.setName("Base");
        assertTrue(cache.add(base));
        base.setName("Base 1");
        cache.update(base);
        Base expected = new Base(1, 1);
        expected.setName("Base 1");
        Base actual = cache.get(1);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getVersion(), actual.getVersion());
        assertEquals(expected.getName(), actual.getName());
    }

    /**
     * Проверка исключения при обновлении с неверной версией объекта.
     */
    @Test(expected = OptimisticException.class)
    public void whenUpdateProblemVersionThenException() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        base.setName("Base");
        assertTrue(cache.add(base));
        base.setName("Base 1");
        cache.update(base);
        Base other = Base.of(base);
        other.setName("Other");
        cache.update(other);
    }

    /**
     * Проверка удаления элемента из кеша.
     */
    @Test
    public void whenDeleteThenNotHaveInCache() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        assertTrue(cache.add(base));
        cache.delete(base);
        assertNull(cache.get(1));
    }
}