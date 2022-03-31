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
        System.out.printf("%s : %s%n", first.getState(), second.getState());
        first.start();
        second.start();
        int count = 0;
        while (first.getState() != Thread.State.TERMINATED && second.getState() != Thread.State.TERMINATED) {
            System.out.printf("%s : %s%n", first.getState(), second.getState());
            count++;
        }
        System.out.printf("%s : %s%n", first.getState(), second.getState());
        System.out.println(count);
        System.out.println("Завершение работы программы");
    }
}
