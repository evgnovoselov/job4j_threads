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
        System.out.println("cur " + Thread.currentThread().getState());
        System.out.println("first " + first.getState());
        System.out.println("second " + second.getState());
        System.out.println();
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED) {
            System.out.println("cur " + Thread.currentThread().getState());
            System.out.println("first " + first.getState());
            System.out.println("second " + second.getState());
            System.out.println();
        }
        System.out.println("cur " + Thread.currentThread().getState());
        System.out.println("first " + first.getState());
        System.out.println("second " + second.getState());
        System.out.println();
    }
}
