package com.myself.java.test.threadTest;

public class SynchronizedTest {

    public static void main(String[] args) {
        final SynchronizedTest test = new SynchronizedTest();

        new Thread(() -> {
            try {
                synchronized (test){
                    System.out.println("Non-static start.");
                    Thread.sleep(2000);
                    System.out.println("Non-static end.");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }).start();

        new Thread(()-> test.syncFunction5()).start();
        new Thread(()-> test.syncFunction1()).start();
        new Thread(()-> test.syncFunction2()).start();


        new Thread(() -> {
            try {
                synchronized (SynchronizedTest.class){
                    System.out.println("Static start.");
                    Thread.sleep(2000);
                    System.out.println("Static end.");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }).start();

        new Thread(()-> test.syncFunction3()).start();
        new Thread(()-> test.syncFunction4()).start();

    }

    public SynchronizedTest(){

    }

    public void syncFunction5(){
        try{
            System.out.println("SyncFunction5 start.");
            Thread.sleep(2000);
            System.out.println("SyncFunction5 end.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized void syncFunction1(){
        try{
            System.out.println("SyncFunction1 start.");
            Thread.sleep(2000);
            System.out.println("SyncFunction1 end.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized void syncFunction2(){
        try{
            System.out.println("SyncFunction2 start.");
            Thread.sleep(2000);
            System.out.println("SyncFunction2 end.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static synchronized void syncFunction3(){
        try{
            System.out.println("SyncFunction3 start.");
            Thread.sleep(2000);
            System.out.println("SyncFunction3 end.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized static void syncFunction4(){
        try{
            System.out.println("SyncFunction4 start.");
            Thread.sleep(2000);
            System.out.println("SyncFunction4 end.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
