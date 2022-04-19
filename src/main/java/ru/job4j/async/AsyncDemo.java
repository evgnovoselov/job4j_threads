package ru.job4j.async;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Класс для демонтрации асинхронизации.
 */
public class AsyncDemo {
    private static void iWork() throws InterruptedException {
        int count = 0;
        while (count < 10) {
            System.out.println("Вы: Я работаю.");
            TimeUnit.SECONDS.sleep(1);
            count++;
        }
    }

    public static CompletableFuture<Void> goToTrash() {
        return CompletableFuture.runAsync(() -> {
            System.out.println("Сын: Мам/Пап, я пошел выносить мусор.");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Сын: Мам/Пап, я вернулся!");
        });
    }

    public static void runAsyncExample() throws InterruptedException {
        CompletableFuture<Void> gtt = goToTrash();
        iWork();
    }

    public static CompletableFuture<String> buyProduct(String product) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Сын: Мам/Пап, я пошел в магазин.");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Сын: Мам/Пап, я купил " + product + ".");
            return product;
        });
    }

    public static void supplyAsyncExample() throws ExecutionException, InterruptedException {
        CompletableFuture<String> bp = buyProduct("Молоко");
        iWork();
        System.out.println("Куплено: " + bp.get());
    }

    public static void thenRunExample() throws InterruptedException {
        CompletableFuture<Void> gtt = goToTrash();
        gtt.thenRun(() -> {
            int count = 0;
            while (count < 3) {
                System.out.println("Сын: Я мою руки.");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                count++;
            }
            System.out.println("Сын: Я помыл руки.");
        });
        iWork();
    }

    public static void thenAcceptExample() throws ExecutionException, InterruptedException {
        CompletableFuture<String> bp = buyProduct("Молоко");
        bp.thenAccept(product -> System.out.println("Сын: Я убрал " + product + " в холодильник."));
        iWork();
        System.out.println("Куплено: " + bp.get());
    }

    public static void thenApplyExample() throws ExecutionException, InterruptedException {
        CompletableFuture<String> bp = buyProduct("Молоко")
                .thenApply(product -> "Сын: Я налил тебе в кружку " + product + ". Держи.");
        iWork();
        System.out.println(bp.get());
    }

    public static void thenComposeExample() throws InterruptedException {
        CompletableFuture<Void> result = buyProduct("Молоко").thenCompose(a -> goToTrash());
        iWork();
    }

    public static void thenCombineExample() throws InterruptedException, ExecutionException {
        CompletableFuture<String> result = buyProduct("Молоко")
                .thenCombine(buyProduct("Хлеб"), (r1, r2) -> "Куплены " + r1 + " и " + r2);
        iWork();
        System.out.println(result.get());
    }

    public static CompletableFuture<Void> washHands(String name) {
        return CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(name + ", моет руки.");
        });
    }

    public static void allOfExample() throws InterruptedException {
        CompletableFuture<Void> all = CompletableFuture.allOf(washHands("Папа"), washHands("Мама"),
                washHands("Ваня"), washHands("Боря"));
        TimeUnit.SECONDS.sleep(3);
    }

    public static CompletableFuture<String> whoWashHands(String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return name + ", моет руки.";
        });
    }

    public static void anyOfExample() throws ExecutionException, InterruptedException {
        CompletableFuture<Object> first = CompletableFuture.anyOf(whoWashHands("Папа"), whoWashHands("Мама"),
                whoWashHands("Ваня"), whoWashHands("Боря"));
        System.out.println("Кто сейчас моет руки?");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(first.get());
    }

    /**
     * Считаем сумму диагоналей матрицы, асинхронно.
     *
     * @param matrix Матрица.
     * @return Массив сум диагоналей матрицы.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static int[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        int[] sums = new int[2 * n];
        Map<Integer, CompletableFuture<Integer>> futures = new HashMap<>();
        futures.put(0, getTask(matrix, 0, n - 1, n - 1));
        for (int k = 1; k <= n; k++) {
            futures.put(k, getTask(matrix, 0, k - 1, k - 1));
            if (k < n) {
                futures.put(2 * n - k, getTask(matrix, n - k, n - 1, n - 1));
            }
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    public static CompletableFuture<Integer> getTask(int[][] data, int startRow, int endRow, int startCol) {
        return CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            int col = startCol;
            for (int i = startRow; i <= endRow; i++) {
                sum += data[i][col];
                col--;
            }
            return sum;
        });
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String[] actions = new String[]{"runAsyncExample", "supplyAsyncExample",
                "thenRunExample", "thenAcceptExample", "thenApplyExample",
                "thenComposeExample", "thenCombineExample", "allOfExample", "anyOfExample", "asyncSum"};
        Scanner scanner = new Scanner(System.in);
        int action = 0;
        do {
            System.out.println("Введите какой пример запустить или выберете выйти - 0");
            System.out.println("Примеры:");
            for (int i = 0; i < actions.length; i++) {
                System.out.printf("%s) %s%n", i + 1, actions[i]);
            }
            System.out.printf("Выберите от %s до %s : ", 0, actions.length);
            action = scanner.nextInt();
            if (action == 1) {
                runAsyncExample();
            }
            if (action == 2) {
                supplyAsyncExample();
            }
            if (action == 3) {
                thenRunExample();
            }
            if (action == 4) {
                thenAcceptExample();
            }
            if (action == 5) {
                thenApplyExample();
            }
            if (action == 6) {
                thenComposeExample();
            }
            if (action == 7) {
                thenCombineExample();
            }
            if (action == 8) {
                allOfExample();
            }
            if (action == 9) {
                anyOfExample();
            }
            if (action == 10) {
                int[][] matrix = new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                };
                System.out.println(Arrays.toString(asyncSum(matrix)));
            }
        } while (action != 0);
    }
}
