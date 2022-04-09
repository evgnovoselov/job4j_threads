package ru.job4j.wait.task;

public class CountBarrierDemo {
    public static void main(String[] args) {
        int total = 5;
        CountBarrier countBarrier = new CountBarrier(total);
        Thread master = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started");
            try {
                for (int i = 0; i < total; i++) {
                    System.out.println(i);
                    countBarrier.count();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Master");
        Thread slave1 = new Thread(() -> {
            countBarrier.await();
            System.out.println(Thread.currentThread().getName() + " started");
        }, "Slave1");
        Thread slave2 = new Thread(() -> {
            countBarrier.await();
            System.out.println(Thread.currentThread().getName() + " started");
        }, "Slave2");
        master.start();
        slave1.start();
        slave2.start();
    }
}
