package com.myself.java.test;

public abstract class AbstractTest implements InterfaceTest {
    public int test2 = 4;

    public static TestClass testClass1 = new TestClass("TestClass1");

    static {
        System.out.println("AbstractTest static init1");
    }

    public static TestClass testClass2 = new TestClass("TestClass2");

    public TestClass testClass3 = new TestClass("TestClass3");

    {
        System.out.println("AbstractTest init1");
    }

    public TestClass testClass4 = new TestClass("TestClass4");


    static {
        System.out.println("AbstractTest static init2");
    }


    {
        System.out.println("AbstractTest init2");
    }

    public void test() {
        System.out.println("AbstractTest function test");
    }


    public final void finalTest() {
        System.out.println("This is final method.");
    }

    AbstractTest() {
        System.out.println("AbstractTest construct");
    }

    abstract public void test(String a);

    public void test2(String a) {
        System.out.println("AbstractTest function test2 " + a);
    }
}
