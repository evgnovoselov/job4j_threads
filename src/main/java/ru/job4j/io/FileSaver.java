package ru.job4j.io;

import java.io.*;

public final class FileSaver implements ContentSaver {
    private final File file;

    public FileSaver(File file) {
        this.file = file;
    }

    @Override
    public synchronized void save(String content) {
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                out.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
