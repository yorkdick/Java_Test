package com.myself.java.test;

import com.myself.java.test.classloadertest.FileClassLoader;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileClassLoader fileClassLoader = new FileClassLoader("/Users/yubo/Documents/培训/loaderTest");
        final Class<?> simpleTest = fileClassLoader.loadClass("com.myself.java.test.classloadertest.SimpleTest");
        System.out.println(simpleTest.getClassLoader() + " " + simpleTest);

    }
}
