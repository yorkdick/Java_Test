package com.myself.java.test.proxyTest;

public class CglibProxyTest {
    public static void main(String[] args) {
        NoInterfaceClass target = new NoInterfaceClass();
        target.function1();
        target.function2("4444");
        System.out.println(target.toString());

        NoInterfaceClass proxyInstance = new CglibProxyFactory<>(target).getProxyInstance();
        proxyInstance.function1();
        proxyInstance.function2("4444");

    }
}
