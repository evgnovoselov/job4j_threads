package ru.job4j.async;

import java.util.Objects;

/**
 * Класс считает суммы по строкам и столбцам квадратной матрицы.
 */
public class RowColSum {
    public static class Sums {
        private int rowSum = 0;
        private int colSum = 0;

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

    public static Sums[] sum(int[][] matrix) {
        Sums[] result = new Sums[matrix.length];
        for (int index = 0; index < result.length; index++) {
            result[index] = new Sums();
        }
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                result[row].rowSum += matrix[row][col];
                result[col].colSum += matrix[row][col];
            }
        }
        return result;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        return new Sums[0];
    }
}
