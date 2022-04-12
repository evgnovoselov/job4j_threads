package ru.job4j.nonblock.cache;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
