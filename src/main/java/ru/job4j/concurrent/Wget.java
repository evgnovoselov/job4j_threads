package ru.job4j.concurrent;

/**
 * Класс эмулирует вывод прогресса загрузки.
 */
public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int index = 0; index <= 100; index++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("\rLoading : %d %%", index);
            }
        });
        thread.start();
    }
}
