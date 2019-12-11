package com.myself.java.test.threadTest;

import java.util.concurrent.atomic.AtomicInteger;

public class WaitTest {

    public static int count = 0;
    public static byte[] producerStatus = new byte[0];
    public static byte[] consumerStatus = new byte[0];

    public static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    if (count > 0) {
                        count--;
                        System.out.println(Thread.currentThread().getName() + " consume 1, left " + count + ".");
                        Thread.sleep(1000);
                    } else {
                        synchronized (producerStatus) {
                            System.out.println("All producer was notified.");
                            producerStatus.notifyAll();
                        }
                        synchronized (consumerStatus) {
                            consumerStatus.wait();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class Producer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    count ++;
                    System.out.println(Thread.currentThread().getName() + " produce 1, left " + count + ".");
                    if (count >= 10) {
                        synchronized (consumerStatus) {
                            consumerStatus.notifyAll();
                            System.out.println("All consumer was notified.");
                        }
                        synchronized (producerStatus) {
                            System.out.println("Wait for consuming.");
                            producerStatus.wait();
                            Thread.sleep(5000);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread producer = new Thread(new Producer(),"Producer");
        Thread consumer1 = new Thread(new Consumer(),"consumer1");
        Thread consumer2 = new Thread(new Consumer(),"consumer2");

        producer.start();
        consumer1.start();
        consumer2.start();
    }
}
