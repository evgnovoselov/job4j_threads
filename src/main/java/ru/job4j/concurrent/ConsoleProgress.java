package ru.job4j.concurrent;

/**
 * Прерывание прогресса в консоле.
 */
public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        char[] process = new char[]{'\\', '|', '/'};
        int index = 0;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\rLoading: " + process[index++]);
                if (index == process.length) {
                    index = 0;
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            return;
        }
    }

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progress.interrupt();
    }
}
