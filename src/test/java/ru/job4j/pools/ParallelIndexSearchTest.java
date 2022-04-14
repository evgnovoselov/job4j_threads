package ru.job4j.pools;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Класс тестирования параллельного поиска индекса объекта в массиве.
 */
public class ParallelIndexSearchTest {
    /**
     * Проверяем поиск индекса, когда элемент есть в массиве.
     */
    @Test
    public void whenHaveObjectInArrayThenGetIndex() {
        Integer[] nums = new Integer[]{5, 6, 4, 7, 3, 8, 2, 9, 1, 0};
        assertEquals(3, ParallelIndexSearch.search(7, nums));
    }

    /**
     * Проверяем поиск индекса, когда элемент нет в массиве.
     */
    @Test
    public void whenNotHaveObjectInArrayThenGetNOHAVE() {
        Integer[] nums = new Integer[]{5, 6, 4, 10, 3, 8, 2, 9, 1, 0};
        assertEquals(ParallelIndexSearch.NO_HAVE, ParallelIndexSearch.search(7, nums));
    }

    /**
     * Проверяем когда массив пустой.
     */
    @Test
    public void whenArrayIsEmptyThenGetNOHAVE() {
        Integer[] nums = new Integer[]{5, 6, 4, 10, 3, 8, 2, 9, 1, 0};
        assertEquals(ParallelIndexSearch.NO_HAVE, ParallelIndexSearch.search(7, nums));
    }
}