package com.myself.java.test.threadTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static Lock lock = new ReentrantLock();

    public static CountDownLatch countDown = new CountDownLatch(3);

    public static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();

        long start = System.currentTimeMillis();

//        final Thread thread1 = new Thread(() -> lockTest.add());
//        final Thread thread2 = new Thread(() -> lockTest.add());
//        final Thread thread3 = new Thread(() -> lockTest.add());

//        new Thread(()-> {
//            countDown.countDown();
//            countDown.countDown();
//            countDown.countDown();
//        }).start();
        final Thread thread1 = new Thread(() -> lockTest.lockAdd());
        final Thread thread2 = new Thread(() -> lockTest.lockAdd());
        final Thread thread3 = new Thread(() -> lockTest.lockAdd());

        thread1.start();
        thread2.start();
        thread3.start();

        countDown.await();
        System.out.println(i + " " + (System.currentTimeMillis() - start));

    }

    public synchronized void add() {
        try {
            Thread.sleep(2000);
            for (int j = 0; j < 10000; j++) {
//                Thread.sleep(50);
                i++;
            }
            countDown.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void lockAdd() {
        try {
            Thread.sleep(2000);
            lock.lock();
            try {
                for (int j = 0; j < 10000; j++) {
//                    Thread.sleep(10);
                    i++;
                }
            } finally {
                lock.unlock();
            }
            countDown.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
