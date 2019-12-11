package com.myself.java.test.reflectTest;

public class BasicClass {
    private int bint;
    public String bstring;


    public static void bStaticMethod() {
        System.out.println("BasicClass bStaticMethod.");
    }

    private static void bStaticPrivateMethod() {
        System.out.println("BasicClass bStaticPrivateMethod.");
    }

    public void bNonStaticMethod() {
        System.out.println("BasicClass bNonStaticMethod.");
    }

    private void bNonStaticPrivateMethod() {
        System.out.println("BasicClass bNonStaticPrivateMethod.");
    }
}
