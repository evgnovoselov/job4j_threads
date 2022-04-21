package ru.job4j.async;

import org.junit.Test;

import java.util.Arrays;

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
        RolColSum.Sums[] expected = new RolColSum.Sums[3];
        expected[0].setColSum(10);
        expected[0].setRowSum(10);
        expected[1].setColSum(10);
        expected[1].setRowSum(10);
        expected[2].setColSum(10);
        expected[2].setRowSum(10);
        assertEquals(Arrays.stream(expected).toList(), Arrays.stream(actual).toList());
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