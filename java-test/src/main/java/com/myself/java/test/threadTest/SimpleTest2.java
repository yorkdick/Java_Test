package com.myself.java.test.threadTest;

public class SimpleTest2 {

    public static Thread thread2 = new Thread(() -> {
        try {
            for (int i = 0; i < 10; i++) {

                Thread.sleep(1000);
//                if (i == 2) {
//                    Thread.yield();
//                }

                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end.");

    }, "Thread2");

    public static Thread thread1 = new Thread(() -> {
        try {
            for (int i = 0; i < 10; i++) {

//                Thread.sleep(1000);
                if (i == 4) {
//                    thread2.start();
//                    thread2.join();
                }

                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end.");

    }, "Thread1");

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Main thread1");

        thread1.start();
//        Thread.sleep(3000);
        thread1.interrupt();
//        thread1.join();

        System.out.println("Main thread2");

    }
}

