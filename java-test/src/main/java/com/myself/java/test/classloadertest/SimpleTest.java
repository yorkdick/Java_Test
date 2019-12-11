package com.myself.java.test.classloadertest;

public class SimpleTest {

    public static final int a =0;

    static{
        System.out.println(ClassLoader.getSystemClassLoader() + " SimpleTest"+" a:"+a);
    }

    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
    }

    public static void print() {
        System.out.println(ClassLoader.getSystemClassLoader() + " SimpleTest");
    }
}
