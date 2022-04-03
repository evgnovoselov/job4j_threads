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
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream out = new FileOutputStream("files/tmp/pom_tmp.xml")) {
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
    }

    public static void main(String[] args) throws InterruptedException {
        String url = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        int speed = 1024;
        if (args.length > 2) {
            url = args[0];
            speed = Integer.parseInt(args[1]);
        }
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
