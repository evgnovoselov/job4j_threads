package ru.job4j.store;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserStoreTest {
    /**
     * Проверяем добавление пользователей с одинаковыми id.
     */
    @Test
    public void whenAddUsersSameIdThenNotAddSecondUser() {
        Store store = new UserStore();
        assertTrue(store.add(new User(1, 50)));
        assertFalse(store.add(new User(1, 200)));
    }

    /**
     * Проверяем получение всех пользователей.
     */
    @Test
    public void whenAdd2UsersAndFindAllThenGetUsers() {
        Store store = new UserStore();
        store.add(new User(1, 50));
        store.add(new User(2, 200));
        assertEquals(List.of(new User(1, 100), new User(2, 200)), store.findAll());
    }

    /**
     * Проверяем обновление пользователя в хранилище.
     */
    @Test
    public void whenUpdateHaveUserThenUpdate() {
        Store store = new UserStore();
        store.add(new User(1, 100));
        assertTrue(store.update(new User(1, 500)));
        assertEquals(500, store.findById(1).orElseThrow().getAmount());
    }

    /**
     * Проверяем обновление пользователя которого нет в хранилище.
     */
    @Test
    public void whenUpdateNotHaveUserThenNotUpdate() {
        Store store = new UserStore();
        assertFalse(store.update(new User(2, 500)));
        assertEquals(List.of(), store.findAll());
    }

    @Test
    public void whenUserTransferMoneyThenWriteMoney() {
        Store store = new UserStore();
        store.add(new User(1, 100));
        store.add(new User(2, 200));
        assertTrue(store.transfer(1, 2, 50));
        assertEquals(50, store.findById(1).orElseThrow().getAmount());
        assertEquals(250, store.findById(2).orElseThrow().getAmount());
    }

    @Test
    public void whenUserNoHaveMoneyForTransferThenNotTransfer() {
        Store store = new UserStore();
        store.add(new User(1, 50));
        store.add(new User(2, 200));
        boolean actual = store.transfer(1, 2, 100);
        assertFalse(actual);
        assertEquals(50, store.findById(1).orElseThrow().getAmount());
        assertEquals(200, store.findById(2).orElseThrow().getAmount());
    }
}