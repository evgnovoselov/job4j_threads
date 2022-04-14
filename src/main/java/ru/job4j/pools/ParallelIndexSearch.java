package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Поиск индекса объекта в массиве объектов с использованием пула.
 */
public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {
    private final T object;
    private final T[] array;
    private final int from;
    private final int to;

    public ParallelIndexSearch(T object, T[] array, int from, int to) {
        this.object = object;
        this.array = array;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        return null;
    }

    public static <T> int search(T object, T[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch<T>(object, array, 0, array.length - 1));
    }

}
