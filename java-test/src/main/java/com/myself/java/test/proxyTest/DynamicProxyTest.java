package com.myself.java.test.proxyTest;

import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    public static void main(String[] args) {
        ProxyInterface proxyInterface = new NoramlClass();
        ProxyInterface proxy = getProxyInstance(proxyInterface);

        proxy.function1();
        proxy.function2("4444");
    }

    public static <T> T getProxyInstance(T obj){
        return (T)Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),(proxy,method,args)->{
                    System.out.println("Dynamic before invoke method.");
                    Object returnValue = method.invoke(obj, args);
                    System.out.println("Dynamic after invoke method.");
                    return returnValue;
                });
    }
}
