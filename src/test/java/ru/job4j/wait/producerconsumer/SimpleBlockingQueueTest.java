package ru.job4j.wait.producerconsumer;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    /**
     * Вводим значение в блокирующую очередь.
     */
    @Test
    public void offer() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Thread producer = new Thread(() -> {
            queue.offer(1);
            queue.offer(2);
        });
        Thread consumer = new Thread(() -> {
            System.out.println(queue.poll());
            System.out.println(queue.poll());
        });
        assertTrue(true);
    }

    /**
     * TODO Комментарий.
     */
    @Test
    public void poll() {
        assertTrue(true);
    }
}