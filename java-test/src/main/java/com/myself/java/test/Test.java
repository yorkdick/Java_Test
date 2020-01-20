package com.myself.java.test;

import com.myself.java.test.classloadertest.FileClassLoader;
import com.myself.java.test.classloadertest.SimpleTest;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("SystemLoader:" + systemClassLoader);
        System.out.println("ContextLoader:" + Thread.currentThread().getContextClassLoader());

        FileClassLoader fileClassLoader = new FileClassLoader("/Users/yubo/Documents/培训/loaderTest");


        System.out.println(systemClassLoader.loadClass("com.myself.java.test.classloadertest.SimpleTest").getClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader().loadClass("com.myself.java.test.classloadertest.SimpleTest").getClassLoader());

        Thread.currentThread().setContextClassLoader(fileClassLoader);
        System.out.println(fileClassLoader.loadClass("com.myself.java.test.classloadertest.SimpleTest").getClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader().loadClass("com.myself.java.test.classloadertest.SimpleTest").getClassLoader());
    }
}
