package ru.job4j.store;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserStoreTest {
    @Test
    public void whenAddUsersSameIdThenNotAddSecondUser() {
        Store store = new UserStore();
        assertTrue(store.add(new User(1, 50)));
        assertFalse(store.add(new User(1, 200)));
    }

    @Test
    public void whenUserTransferMoneyThenWriteMoney() {
        Store store = new UserStore();
        store.add(new User(1, 100));
        store.add(new User(2, 200));
        assertTrue(store.transfer(1, 2, 50));
        assertEquals(50, store.findById(1).getAmount());
        assertEquals(250, store.findById(2).getAmount());
    }

    @Test
    public void whenUserNoHaveMoneyForTransferThenNotTransfer() {
        Store store = new UserStore();
        store.add(new User(1, 50));
        store.add(new User(2, 200));
        boolean actual = store.transfer(1, 2, 100);
        assertFalse(actual);
        assertEquals(50, store.findById(1).getAmount());
        assertEquals(200, store.findById(2).getAmount());
    }
}