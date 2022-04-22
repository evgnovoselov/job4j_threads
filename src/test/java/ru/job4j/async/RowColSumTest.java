package ru.job4j.async;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

/**
 * Тестирования класса, который считает суммы по строкам и столбцам квадратной матрицы.
 */
public class RowColSumTest {
    /**
     * Тестирование линейного метода подсчета суммы строк и столбцов в квадратной матрице.
     */
    @Test
    public void whenQuadMatrixCalLinearSumThenArraysSums() {
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
     * Тестирование асинхроного метода подсчета суммы строк и столбцов в квадратной матрице.
     */
    @Test
    public void whenQuadMatrixCalAsyncSumThenArraysSums() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RowColSum.Sums[] actual = RowColSum.asyncSum(matrix);
        RowColSum.Sums[] expected = new RowColSum.Sums[]{
                new RowColSum.Sums(6, 12),
                new RowColSum.Sums(15, 15),
                new RowColSum.Sums(24, 18)
        };
        assertEquals(Arrays.stream(expected).toList(), Arrays.stream(actual).toList());
    }
}