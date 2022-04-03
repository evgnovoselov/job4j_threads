package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Класс загружает файл и выводит прогресса загрузки.
 */
public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream out = new FileOutputStream("files/tmp/" + fileName)) {
            byte[] dataBuffer = new byte[speed];
            int byteRead;
            while ((byteRead = in.read(dataBuffer, 0, speed)) != -1) {
                out.write(dataBuffer, 0, byteRead);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        progress.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
