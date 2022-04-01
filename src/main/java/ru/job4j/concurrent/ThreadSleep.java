package ru.job4j.concurrent;

/**
 * Проверка перевода нить в статус TIMED_WAITING.
 */
public class ThreadSleep {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Start loading...");
                Thread.sleep(3000);
                System.out.println("Loaded.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("Main");
    }
}
