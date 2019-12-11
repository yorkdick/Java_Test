package com.myself.java.test.threadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {
    public static AtomicInteger a = new AtomicInteger(0);

    public static boolean bool = false;


    public static Callable callable = () -> {
        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(20);
                a.incrementAndGet();
//                a.addAndGet(4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Object();
    };

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testAdd();
//        testVolatile();

    }


    public static void testVolatile() {
        new Thread(() -> {
            try {
                while (!bool) {
                    System.out.println("False.........");
//                    Thread.sleep(1000);
                }
                System.out.println("True... end.");
            } catch (Exception e) {

            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    i++;
                    Thread.sleep(1000);
                    if (i > 5) {
                        bool = true;
                        System.out.println("Set to ..... true");
                        break;
                    }
                }
            } catch (Exception e) {

            }
        }).start();
    }

    public static void testAdd() throws ExecutionException, InterruptedException {
        final FutureTask<Object> task1 = new FutureTask<Object>(callable);
        final FutureTask<Object> task2 = new FutureTask<Object>(callable);
        new Thread(task1).start();
        new Thread(task2).start();

        task1.get();
        task2.get();

        System.out.println(a.get());
    }
}
