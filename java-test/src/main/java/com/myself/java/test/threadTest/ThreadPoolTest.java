package com.myself.java.test.threadTest;

import java.util.concurrent.*;

public class ThreadPoolTest {

    public static Runnable runnable = () -> {
        try {
            System.out.println("Task start...");
            for (int j = 0; j < 10; j++) {
                System.out.println(Thread.currentThread().getName() + " " + j);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public static void main(String[] args) throws InterruptedException {
        fixedPoolTest();
//        cachedPoolTest();
//        singlePoolTest();
//        scheduledPoolTest();
    }

    public static void scheduledPoolTest() throws InterruptedException {
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        //延迟多少秒执行一次
//        scheduledExecutorService.schedule(runnable,2000,TimeUnit.MILLISECONDS);

        //每隔长时间执行一次，间隔指的是每次线程开始执行时间间隔
        final ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(runnable, 0, 1000, TimeUnit.MILLISECONDS);
        Thread.sleep(5000);
        scheduledFuture.cancel(true);

        //每隔多长时间执行一次，间隔指的是下一次开始时间和上一次结束时间的间隔
//        scheduledExecutorService.scheduleWithFixedDelay(runnable,0,5000,TimeUnit.MILLISECONDS);
    }

    public static void singlePoolTest() {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 4; i++) {
            executorService.execute(runnable);
        }
    }

    public static void cachedPoolTest() {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(runnable);
        }
    }

    public static void fixedPoolTest() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            executorService.execute(runnable);
        }
//        executorService.shutdown();
        executorService.shutdownNow();

        executorService.execute(() -> {
        });

        System.out.println("Shutdown threadPool");
        System.out.println(executorService.awaitTermination(2000, TimeUnit.MILLISECONDS));
        ;
        System.out.println("ThreadPool was shutdown.");
    }
}
