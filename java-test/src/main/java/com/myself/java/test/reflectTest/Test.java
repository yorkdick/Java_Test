package com.myself.java.test.reflectTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws Exception {
//        initTest();
//        getFiled(ReflectTest.class);
//        getAllFields(ReflectTest.class);
//        getMethod(ReflectTest.class);
//        getAllMethods(ReflectTest.class);
//        newInstances();
//        setFiled();
        invokeMethod();
    }

    public static void invokeMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clss = ReflectTest.class;
        ReflectTest test = new ReflectTest();

        final Method nonStaticMethod = clss.getDeclaredMethod("nonStaticMethod");
        nonStaticMethod.invoke(test);

        final Method nonStaticMethodWithParam = clss.getDeclaredMethod("nonStaticMethodWithParam",String.class);
        nonStaticMethodWithParam.invoke(test,"test invode method");

        final Method nonStaticPrivateMethod = clss.getDeclaredMethod("nonStaticPrivateMethod");
        nonStaticPrivateMethod.setAccessible(true);
        nonStaticPrivateMethod.invoke(test);
    }

    public static void setFiled() throws NoSuchFieldException, IllegalAccessException {
        Class<?> clss = ReflectTest.class;
        ReflectTest test = new ReflectTest();

        System.out.println("Before set value. Ant2:"+test.getAnInt2());
        final Field anInt2 = clss.getDeclaredField("anInt2");
        anInt2.setInt(test,4);
        System.out.println("After set value. Ant2:"+test.getAnInt2());

        System.out.println("Before set value. Ant:"+test.getAnInt());
        Field anInt = clss.getDeclaredField("anInt");
        System.out.println(anInt.isAccessible());
        anInt.setAccessible(true);
        anInt.setInt(test,4);
        System.out.println(anInt.isAccessible());
        System.out.println("After set value. Ant:"+test.getAnInt());


        anInt = clss.getDeclaredField("anInt");
        System.out.println(anInt.isAccessible());

    }


    public static void newInstances() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Class<?> clss = ReflectTest.class;

        System.out.println("Create with newInstance");
        ReflectTest reflectTest = (ReflectTest)clss.newInstance();
        System.out.println(reflectTest.getAnString());

        System.out.println("Create with nonparam Constructor");
        reflectTest = (ReflectTest)(clss.getConstructor().newInstance());
        System.out.println(reflectTest.getAnString());

        System.out.println("Create with param Constructor");
        reflectTest = (ReflectTest)(clss.getDeclaredConstructor(String.class).newInstance("444444"));
        System.out.println(reflectTest.getAnString());
    }



    public static void getAllFields(Class<?> clss){
        System.out.println("getDeclaredFields");
        Arrays.stream(clss.getDeclaredFields()).forEach(field -> System.out.println(field));

        Class<?> superclass = null;
        while((superclass=clss.getSuperclass())!=Object.class){
            System.out.println("");
            System.out.println("Super class getDeclaredFields");
            Arrays.stream(superclass.getDeclaredFields()).forEach(field -> System.out.println(field));
            clss = superclass;
        }
    }

    public static void getFiled(Class<?> clss) throws NoSuchFieldException {
        System.out.println();
        System.out.println("getFields");
        Arrays.stream(clss.getFields()).forEach(field -> System.out.println(field));

        System.out.println();
        System.out.println("getDeclaredFields");
        Arrays.stream(clss.getDeclaredFields()).forEach(field -> System.out.println(field));

        System.out.println();
        System.out.println("getDeclaredField by name");
        System.out.println(clss.getDeclaredField("anString"));
        System.out.println(clss.getDeclaredField("anInt2"));

        System.out.println();
        System.out.println("getFiled by name");
        System.out.println(clss.getField("anInt2"));
        System.out.println(clss.getField("anString"));
    }

    public static void getAllMethods(Class<?> clss){
        System.out.println("getDeclaredMethods");
        Arrays.stream(clss.getDeclaredMethods()).forEach(obj -> System.out.println(obj));

        Class<?> superclass = null;
        while((superclass=clss.getSuperclass())!=Object.class){
            System.out.println("");
            System.out.println("Super class getDeclaredMethods");
            Arrays.stream(superclass.getDeclaredMethods()).forEach(obj -> System.out.println(obj));
            clss = superclass;
        }
    }

    public static void getMethod(Class<?> clss) throws NoSuchFieldException, NoSuchMethodException {
        System.out.println("getMethods");
        Arrays.stream(clss.getMethods()).forEach(obj -> System.out.println(obj));

        System.out.println();
        System.out.println("getDeclaredMethods");
        Arrays.stream(clss.getDeclaredMethods()).forEach(obj -> System.out.println(obj));

        System.out.println();
        System.out.println("getDeclaredMethod by name");
        System.out.println(clss.getDeclaredMethod("staticMethod"));
        System.out.println(clss.getDeclaredMethod("nonStaticPrivateMethod"));

        System.out.println();
        System.out.println("getMethod by name");
        System.out.println(clss.getMethod("staticMethod"));
        System.out.println(clss.getMethod("nonStaticPrivateMethod"));
    }

    public static void initTest() throws ClassNotFoundException {
        Class clss = ReflectTest.class;
        System.out.println(".class 不会初始化类。");

        System.out.println(ReflectTest.b);
        System.out.println("调用静态方法或者静态变量会初始化类。");

        clss = Class.forName("com.myself.java.test.reflectTest.ReflectTest");
        System.out.println("forName 会初始化类。");
    }
}
