package ru.job4j.async;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Тестирования класса, который считает суммы по строкам и столбцам квадратной матрицы.
 */
public class RowColSumTest {
    /**
     * TODO Реализовать тест и переименовать метод.
     */
    @Test
    public void sum() {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RowColSum.Sums[] actual = RowColSum.sum(matrix);
        RowColSum.Sums[] expected = new RowColSum.Sums[]{
                new RowColSum.Sums(6, 12),
                new RowColSum.Sums(15, 15),
                new RowColSum.Sums(24, 18)
        };
        assertEquals(Arrays.stream(expected).toList(), Arrays.stream(actual).toList());
    }

    /**
     * TODO Реализовать тест и переименовать метод.
     */
    @Test
    public void asyncSum() {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        assertTrue(true);
    }
}