package ru.job4j.wait.producerconsumer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class SimpleBlockingQueueTest {

    /**
     * Проверка работы блокирующей очереди.
     */
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(1);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5 && !Thread.interrupted(); i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        producer.start();
        Thread consumer = new Thread(() -> {
            while (!queue.isEmpty() || !Thread.interrupted()) {
                try {
                    buffer.add(queue.poll());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertEquals(List.of(0, 1, 2, 3, 4), buffer);
    }

    /**
     * Проверка ввода и получение информации с блокирующей очереди.
     */
    @Test
    public void whenOfferNumsThenNums() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> actual = new ArrayList<>();
        Thread producer = new Thread(() -> {
            try {
                for (Integer num : nums) {
                    queue.offer(num);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread consumer = new Thread(() -> {
            while (!queue.isEmpty() || !Thread.interrupted()) {
                try {
                    actual.add(queue.poll());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertEquals(nums, actual);
    }

    /**
     * Проверка статуса ожидания нити вставки, больше лимита.
     */
    @Test
    public void whenOfferNumsMoreLimitThenThreadStatusWait() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(1);
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Thread producer = new Thread(() -> {
            try {
                for (Integer num : nums) {
                    queue.offer(num);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producer.start();
        producer.join(100);
        assertEquals(Thread.State.WAITING, producer.getState());
    }

    /**
     * Проверка статуса ожидания нити получения, когда элементов нет.
     */
    @Test
    public void whenGiveThenThreadStatusWait() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        Thread consumer = new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        consumer.start();
        consumer.join(100);
        assertEquals(Thread.State.WAITING, consumer.getState());
    }
}