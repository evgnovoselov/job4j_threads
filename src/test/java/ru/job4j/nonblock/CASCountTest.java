package ru.job4j.nonblock;

import org.junit.Test;

import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void whenIncrementThenIncrement() {
        CASCount count = new CASCount();
        count.increment();
        count.increment();
        count.increment();
        assertEquals(3, count.get());
    }
}