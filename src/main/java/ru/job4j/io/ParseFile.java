package ru.job4j.io;

import java.io.*;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public String getContent() {
        StringBuilder output = new StringBuilder();
        try (InputStream in = new FileInputStream(file)) {
            int data;
            while ((data = in.read()) > 0) {
                output.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public String getContentWithoutUnicode() {
        StringBuilder output = new StringBuilder();
        try (InputStream in = new FileInputStream(file)) {
            int data;
            while ((data = in.read()) > 0) {
                if (data < 0x80) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public void saveContent(String content) throws IOException {
        OutputStream out = new FileOutputStream(file);
        for (int i = 0; i < content.length(); i += 1) {
            out.write(content.charAt(i));
        }
    }
}
