package ru.job4j.pool;

import ru.job4j.wait.producerconsumer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool() {
        this(1000);
    }

    public ThreadPool(int limitQueue) {
        tasks = new SimpleBlockingQueue<>(limitQueue);
        int size = Runtime.getRuntime().availableProcessors();
        IntStream.range(0, size).forEach(value -> threads.add(new Thread(() -> {
            try {
                tasks.poll().run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        })));
    }

    /**
     * Добавляем задачи на выполнение.
     *
     * @param run Задача.
     */
    public void work(Runnable run) {
        try {
            tasks.offer(run);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Завершение всех задач.
     */
    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }
}
