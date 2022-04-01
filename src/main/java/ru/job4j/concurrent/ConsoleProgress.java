package ru.job4j.concurrent;

/**
 * Прерывание прогресса в консоле.
 */
public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        char[] process = new char[]{'\\', '|', '/'};
        int index = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\rLoading: " + process[index++]);
            if (index == process.length - 1) {
                index = 0;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}
