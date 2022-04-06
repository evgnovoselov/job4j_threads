package ru.job4j.io;

import java.util.function.Predicate;

public interface Parser {
    String getContent();

    String getContentByPredicate(Predicate<Character> filter);
}
