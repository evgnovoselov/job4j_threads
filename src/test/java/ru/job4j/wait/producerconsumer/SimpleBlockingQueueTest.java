package ru.job4j.wait.producerconsumer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimpleBlockingQueueTest {
    /**
     * Проверка ввода и получение информации с блокирующей очереди.
     */
    @Test
    public void whenOfferNumsThenNums() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> actual = new ArrayList<>();
        Thread producer = new Thread(() -> {
            nums.forEach(queue::offer);
        });
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < nums.size(); i++) {
                actual.add(queue.poll());
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertEquals(nums, actual);
    }
}