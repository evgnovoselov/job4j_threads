package ru.job4j.linked;

/**
 * Потокобезопасный класс, описывающий узел односвязного списка.
 *
 * @param <T>
 */
public class Node<T> {
    private final T value;
    private final Node<T> next;

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}
