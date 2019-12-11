package com.myself.java.test.proxyTest;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyFactory<T> implements MethodInterceptor {
    private T target;

    public CglibProxyFactory(T target){
        this.target = target;
    }

    //给目标对象创建一个代理对象
    public T getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return (T)en.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //proxy-代理对象
        //method-被调用的方法
        //methodProxy-代理方法

        System.out.println("开始事务...");
        Object returnValue = method.invoke(target, objects);
        System.out.println("提交事务...");
        return returnValue;
    }
}
