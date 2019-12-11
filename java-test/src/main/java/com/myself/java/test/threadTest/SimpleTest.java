package com.myself.java.test.threadTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class SimpleTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Map<String,String> map = new HashMap<>();
        Map<String,String> map2 = new ConcurrentHashMap<>();

        map2.put("","");
        map2.put("","");
//        threadTest();
//        runnableTest();
//        callableTest();
        poolTest();;

    }

    public static void poolTest(){
        //方法4 线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
        });
    }

    public static void threadTest(){
        //方式1 继承thread
        Thread thread1 = new TestThread("TestThread1");
        thread1.start();
    }

    public static void runnableTest(){
        //方法2 实现runnable
        Thread thread2 = new Thread(new TestRunnable(),"RunnableThread1");

        Thread thread4 = new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
        } ,"RunnableThread4");
        thread2.start();
        thread4.start();
    }

    public static void callableTest() throws ExecutionException, InterruptedException {
        //方法3 实现callable
        final FutureTask<String> futureTask = new FutureTask<>(new CallableThread());

        final FutureTask<String> futureTask2 = new FutureTask<>(()->{
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
            return Thread.currentThread().getName();
        });

        Thread thread3 = new Thread(futureTask,"CallableThread1");
        Thread thread5 = new Thread(futureTask2,"CallableThread2");

        thread3.start();
        thread5.start();

        System.out.println(Thread.currentThread().getName()+" Main thread start.");

        System.out.println("Future get:"+futureTask.get());
        System.out.println("Future get:"+futureTask2.get());

        System.out.println(Thread.currentThread().getName()+" Main thread end.");


    }

    public static class TestThread extends Thread{

        public TestThread(String name){
            super(name);
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+" "+i);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static class TestRunnable implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
        }
    }

    public static class CallableThread implements Callable<String>{

        @Override
        public String call() throws Exception {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
            return Thread.currentThread().getName();
        }
    }
}
