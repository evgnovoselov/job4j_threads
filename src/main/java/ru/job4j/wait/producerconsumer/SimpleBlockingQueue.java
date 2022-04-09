package ru.job4j.wait.producerconsumer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    @GuardedBy("this")
    private final int maxSize;

    public SimpleBlockingQueue() {
        maxSize = -1;
    }

    public SimpleBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void offer(T value) {
        while (!Thread.interrupted() && maxSize > 0 && queue.size() >= maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (!Thread.interrupted()) {
            queue.offer(value);
            notifyAll();
        }
    }

    public synchronized T poll() {
        while (!Thread.interrupted() && queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        T result = queue.poll();
        notifyAll();
        return result;
    }
}
