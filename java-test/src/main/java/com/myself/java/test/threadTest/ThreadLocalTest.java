package com.myself.java.test.threadTest;

public class ThreadLocalTest {

    static ThreadLocal<String> localVar = new ThreadLocal<>();

    static InheritableThreadLocal<String> iLocalVal = new InheritableThreadLocal<>();

    static void print(String str) {
        //打印当前线程中本地内存中本地变量的值
        System.out.println(str + " :" + localVar.get());
        //清除本地内存中的本地变量
        localVar.remove();
    }

    public static void main(String[] args) throws InterruptedException {
//        threadLocalTest();
        inheritableThreadLocalTest();
    }

    public static void inheritableThreadLocalTest() throws InterruptedException {
        iLocalVal.set("iLocalVal");
        System.out.println("Main thread1 "+iLocalVal.get());
        Thread childThread = new Thread(() -> {
//            iLocalVal.set("iLocalVal2");
            System.out.println("Child thread "+iLocalVal.get());
            iLocalVal.set("iLocalVal3");
            System.out.println("Child thread2 "+iLocalVal.get());
            iLocalVal.remove();
            System.out.println("Child thread3 "+iLocalVal.get());

        });
        childThread.start();
        childThread.join();
        System.out.println("Main thread1 "+iLocalVal.get());
    }

    public static void threadLocalTest(){
        Thread t1  = new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程1中本地变量的值
                localVar.set("localVar1");
                //调用打印方法
                print("thread1");
                //打印本地变量
                System.out.println("after remove : " + localVar.get());
            }
        });

        Thread t2  = new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程1中本地变量的值
                localVar.set("localVar2");
                //调用打印方法
                print("thread2");
                //打印本地变量
                System.out.println("after remove : " + localVar.get());
            }
        });

        t1.start();
        t2.start();
    }
}
