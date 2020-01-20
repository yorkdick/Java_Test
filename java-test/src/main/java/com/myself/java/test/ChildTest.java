package com.myself.java.test;

public class ChildTest extends AbstractTest {

    public int test2 = 3;

    public static TestClass testClass5 = new TestClass("TestClass5");

    public TestClass testCass6 = new TestClass("TestClass6");

    static {
        System.out.println("ChildTest static init");
    }

    {
        System.out.println("ChildTest init");
    }

    public void finalTest(String a) {
        System.out.println("Child function test");
    }

    @Override
    public void test() {
        System.out.println("Child function test");
    }

    public static void main(String[] args) {
        ChildTest childTest = new ChildTest();


        System.out.println(childTest.test2);
        childTest.test2("4");

        AbstractTest abstractTest = (AbstractTest) childTest;
        System.out.println(abstractTest.test2);
        abstractTest.test2("4");
    }

    ChildTest() {
        System.out.println("Child construct");
    }

    @Override
    public void test(String a) {
        System.out.println("Child function test " + a);
    }

    @Override
    public void test2(String a) {
        System.out.println("Child function test2 " + a);
    }
}

