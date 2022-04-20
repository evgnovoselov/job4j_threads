package ru.job4j.async;

/**
 * Класс считает суммы по строкам и столбцам квадратной матрицы.
 */
public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

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
    }

    public static Sums[] sum(int[][] matrix) {
        return new Sums[0];
    }

    public static Sums[] asyncSum(int[][] matrix) {
        return new Sums[0];
    }
}
