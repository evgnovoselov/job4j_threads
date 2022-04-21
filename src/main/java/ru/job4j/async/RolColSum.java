package ru.job4j.async;

import java.util.Objects;

/**
 * Класс считает суммы по строкам и столбцам квадратной матрицы.
 */
public class RolColSum {
    public static class Sums {
        private int rowSum = 0;
        private int colSum = 0;

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
        for (int i = 0; i < matrix.length; i++) {
            if (result[i] == null) {
                result[i] = new Sums();
            }
            for (int j = 0; j < matrix[i].length; j++) {
                result[i].rowSum += matrix[i][j];
                result[i].colSum += matrix[j][i];
            }
        }
        return result;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        return new Sums[0];
    }
}
