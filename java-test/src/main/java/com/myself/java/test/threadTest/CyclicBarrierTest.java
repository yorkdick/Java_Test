package com.myself.java.test.threadTest;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);


    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 11; i++) {
            new WaitThread(i % 4, "Thread" + i).start();
        }

        Thread.sleep(3000);
        cyclicBarrier.reset();

    }

    public static class WaitThread extends Thread {

        int waitTime;

        public WaitThread(int waitTime, String name) {
            super(name);
            this.waitTime = waitTime;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(waitTime);
                System.out.println(Thread.currentThread().getName() + " wait for others.");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " done.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
