package com.myself.java.test;

public class TestClass {

    static {
        System.out.println("TestClass static init1");
    }


    TestClass(String a) {
        System.out.println("TestClass construct" + " " + a);
    }

    {
        System.out.println("TestClass init1");
    }
}
