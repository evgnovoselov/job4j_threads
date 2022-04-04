package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Загрузка файла с ограничением по скорости.
 */
public class Wget implements Runnable {
    private static final int LENGTH_BUFFER = 1024;
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream out = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[LENGTH_BUFFER];
            int bytesRead;
            long bytesWritten = 0;
            long startTime = System.currentTimeMillis();
            long deltaTime;
            int sleep;
            while ((bytesRead = in.read(dataBuffer, 0, LENGTH_BUFFER)) != -1) {
                out.write(dataBuffer, 0, bytesRead);
                bytesWritten += bytesRead;
                if (bytesWritten >= speed) {
                    deltaTime = System.currentTimeMillis() - startTime;
                    if (deltaTime < 1000) {
                        sleep = (int) (1000 - deltaTime);
                    } else {
                        sleep = 0;
                    }
                    Thread.sleep(sleep);
                    startTime = System.currentTimeMillis();
                    bytesWritten = 0;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        verification(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }

    private static void verification(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Not set args. Need set args: <url> <speed>. Where speed - byte/s.");
        }
        int speed;
        try {
            speed = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Not valid arg <speed>. Need set args: <url> <speed>. Where speed - byte/s.");
        }
        if (speed < 1) {
            throw new IllegalArgumentException("Not valid arg <speed>. Need set number more 1 byte/s.");
        }
    }
}
