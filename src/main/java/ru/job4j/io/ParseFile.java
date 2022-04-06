package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;
    private final ContentSaver saver;

    public ParseFile(File file, ContentSaver saver) {
        this.file = file;
        this.saver = saver;
    }

    public String getContent() {
        return getContentByPredicate(character -> true);
    }

    public String getContentWithoutUnicode() {
        return getContentByPredicate(character -> character < 0x80);
    }

    public String getContentByPredicate(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) > 0) {
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

    public void saveContent(String content) {
    }
}
