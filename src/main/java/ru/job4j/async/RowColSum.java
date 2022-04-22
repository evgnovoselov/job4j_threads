package ru.job4j.async;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Класс считает суммы по строкам и столбцам квадратной матрицы.
 */
public class RowColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums() {
        }

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }

        @Override
        public String toString() {
            return "Sums{"
                    + "rowSum=" + rowSum
                    + ", colSum=" + colSum
                    + '}';
        }
    }

    /**
     * Линейный подсчет суммы столбцов и строк квадратной матрицы.
     *
     * @param matrix Квадратная матрица.
     * @return Массив суммы строк и столбцов.
     */
    public static Sums[] sum(int[][] matrix) {
        Sums[] result = new Sums[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (result[col] == null) {
                    result[col] = new Sums();
                }
                result[row].rowSum += matrix[row][col];
                result[col].colSum += matrix[row][col];
            }
        }
        return result;
    }

    /**
     * Асинхронный подсчет суммы столбцов и строк квадратной матрицы.
     *
     * @param matrix Квадратная матрица.
     * @return Массив суммы строк и столбцов.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sumsRowCol = new Sums[matrix.length];
        List<CompletableFuture<Sums>> futures = new ArrayList<>();
        for (int index = 0; index < matrix.length; index++) {
            futures.add(getSumRowColByIndex(matrix, index));
        }
        for (int index = 0; index < matrix.length; index++) {
            sumsRowCol[index] = futures.get(index).get();
        }
        return sumsRowCol;
    }

    private static CompletableFuture<Sums> getSumRowColByIndex(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> {
            Sums result = new Sums();
            for (int i = 0; i < matrix.length; i++) {
                result.rowSum += matrix[index][i];
                result.colSum += matrix[i][index];
            }
            return result;
        });
    }
}
