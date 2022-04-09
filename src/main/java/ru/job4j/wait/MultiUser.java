package ru.job4j.wait;

public class MultiUser {
    public static void main(String[] args) {
        Barrier barrier = new Barrier();
        Thread master = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            barrier.on();
        }, "Master");
        Thread slave = new Thread(() -> {
            barrier.check();
            System.out.println(Thread.currentThread().getName() + " started");
        }, "Slave");
        Thread slave2 = new Thread(() -> {
            barrier.check();
            System.out.println(Thread.currentThread().getName() + " started");
        }, "Slave2");
        master.start();
        slave.start();
        slave2.start();
    }
}
