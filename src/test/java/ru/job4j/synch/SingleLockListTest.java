package ru.job4j.synch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class SingleLockListTest {
    /**
     * Проверяем добавление элементов.
     */
    @Test
    public void thenAddWhenHaveElements() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>();
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertEquals(Set.of(1, 2), rsl);
    }

    /**
     * Проверяем созданный список через конструктор со списком и пробуем старый список отформатировать, и проверить
     * чтобы в нашем ни чего не поменялось.
     */
    @Test
    public void thenConstructWithListAndChangeTheirWhenNotChangeOldList() throws InterruptedException {
        List<Integer> nums = new ArrayList<>();
        SingleLockList<Integer> list = new SingleLockList<>(nums);
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        nums.add(3);
        nums.add(3);
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertEquals(Set.of(1, 2), rsl);
    }
}