package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile implements Parser {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    @Override
    public synchronized String getContent() {
        return getContentByPredicate(character -> true);
    }

    public synchronized String getContentWithoutUnicode() {
        return getContentByPredicate(character -> character < 0x80);
    }

    @Override
    public synchronized String getContentByPredicate(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) != -1) {
                char dataChar = (char) data;
                if (filter.test(dataChar)) {
                    output.append(dataChar);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
