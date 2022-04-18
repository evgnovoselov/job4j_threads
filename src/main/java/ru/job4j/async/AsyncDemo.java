package ru.job4j.async;

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

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String[] actions = new String[]{"runAsyncExample", "supplyAsyncExample",
                "thenRunExample", "thenAcceptExample", "thenApplyExample"};
        String action = actions[4];
        if (action.equals(actions[0])) {
            runAsyncExample();
        }
        if (action.equals(actions[1])) {
            supplyAsyncExample();
        }
        if (action.equals(actions[2])) {
            thenRunExample();
        }
        if (action.equals(actions[3])) {
            thenAcceptExample();
        }
        if (action.equals(actions[4])) {
            thenApplyExample();
        }
    }
}
