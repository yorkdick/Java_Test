package com.myself.java.test;

public interface InterfaceTest {
    String test = "4";

    default void testFunction(String a) {
        System.out.println(a);
    }

    static void testFunction2(String a) {
        System.out.println(a);
    }

    void test();
}
