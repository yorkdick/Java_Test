package com.myself.java.test.proxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    public static void main(String[] args) {
        NoramlClass noramlClass = new NoramlClass();
        ProxyInterface proxy = getProxyInstance(noramlClass);

        noramlClass.function1();

        proxy.function1();
        proxy.function2("4444");
    }

    public static <T> T getProxyInstance(T obj){
        ClassLoader classLoader = obj.getClass().getClassLoader();
        Class<?>[] interfaces = obj.getClass().getInterfaces();
        InvocationHandler invocationHandler = new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method targetMethod, Object[] args) throws Throwable {
                System.out.println("Dynamic before invoke method."+targetMethod);

                Object returnValue = targetMethod.invoke(obj, args);

                System.out.println("Dynamic after invoke method."+targetMethod);
                return returnValue;
            }
        };
        return (T)Proxy.newProxyInstance(classLoader,interfaces,invocationHandler);
    }
}
