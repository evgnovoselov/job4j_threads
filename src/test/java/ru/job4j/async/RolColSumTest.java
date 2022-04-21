package ru.job4j.async;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Тестирования класса, который считает суммы по строкам и столбцам квадратной матрицы.
 */
public class RolColSumTest {
    /**
     * TODO Реализовать тест.
     */
    @Test
    public void sum() {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] actual = RolColSum.sum(matrix);
        RolColSum.Sums[] expected = new RolColSum.Sums[0];
        assertTrue(true);
    }

    /**
     * TODO Реализовать тест.
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