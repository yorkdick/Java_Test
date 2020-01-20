package com.myself.java.test.threadTest;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static Semaphore machines = new Semaphore(3);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(runnable, "Worker" + i).start();
        }
    }

    public static Runnable runnable = () -> {
        try {
            machines.acquire();
            try {
                System.out.println(Thread.currentThread().getName() + " get machine and working.");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " return machine.");
            } finally {
                machines.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
}
