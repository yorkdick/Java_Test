package com.myself.java.test.proxyTest;

public class NoramlClass implements ProxyInterface {
    public void function1() {
        System.out.println("NoramlClass function1");
    }

    public void function2(String str) {
        System.out.println("NoramlClass function2, "+str);
    }
}
