package ru.job4j.pool;

import ru.job4j.wait.producerconsumer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(Runtime.getRuntime().availableProcessors());

    /**
     * Добавляем задачи на выполнение.
     *
     * @param run Задача.
     */
    public void work(Runnable run) {

    }

    /**
     * Завершение всех задач.
     */
    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }
}
