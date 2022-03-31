package ru.job4j.concurrent;

/**
 * Запускаем и выводим состояние нитей.
 */
public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        Thread second = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        first.start();
        second.start();
        while (true) {
            if (first.getState() == Thread.State.TERMINATED && second.getState() == Thread.State.TERMINATED) {
                System.out.println("Done! Other threads terminated!");
                break;
            }
        }
    }
}