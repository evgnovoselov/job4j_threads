package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Поиск индекса объекта в массиве объектов с использованием пула.
 */
public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {
    public static final int NO_HAVE = -1;
    private final T search;
    private final T[] array;
    private final int from;
    private final int to;

    public ParallelIndexSearch(T search, T[] array, int from, int to) {
        this.search = search;
        this.array = array;
        this.from = from;
        this.to = to;
    }

    /**
     * Параллельный поиск, когда массив становится по длине меньше или равно 10, используется линейный поиск.
     *
     * @return Индекс элемента или константа NO_HAVE, если элемент отсутствует.
     */
    @Override
    protected Integer compute() {
        if ((to - from) <= 10) {
            return linearSearch();
        }
        int mid = (from + to) / 2;
        ParallelIndexSearch<T> leftSearch = new ParallelIndexSearch<>(search, array, from, mid);
        ParallelIndexSearch<T> rightSearch = new ParallelIndexSearch<>(search, array, mid + 1, to);
        leftSearch.fork();
        rightSearch.fork();
        int left = leftSearch.join();
        int right = rightSearch.join();
        return (left != NO_HAVE) ? left : right;
    }

    private int linearSearch() {
        int result = NO_HAVE;
        for (int index = from; index <= to; index++) {
            if (search.equals(array[index])) {
                result = index;
                break;
            }
        }
        return result;
    }

    public static <T> int search(T search, T[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch<>(search, array, 0, array.length - 1));
    }

}
