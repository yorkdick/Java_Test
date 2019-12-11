package com.myself.java.test.reflectTest;

public class ReflectTest extends BasicClass {
    private int anInt;
    private String anString;
    public int anInt2;

    public static int b = 3;

    static {
        b = 4;
        System.out.println("ReflectTest init.");
    }

    public ReflectTest() {
        System.out.println("Constructor with no param.");
    }


    public ReflectTest(String str) {
        this.anString = str;
        System.out.println("Constructor with param.");
    }

    public static void staticMethod() {
        System.out.println("ReflectTest staticMethod.");
    }

    private static void staticPrivateMethod() {
        System.out.println("ReflectTest staticPrivateMethod.");
    }

    public void nonStaticMethod() {
        System.out.println("ReflectTest nonStaticMethod.");
    }

    public void nonStaticMethodWithParam(String str) {
        System.out.println("ReflectTest nonStaticMethodWithParam." + "  " + str);
    }

    private void nonStaticPrivateMethod() {
        System.out.println("ReflectTest nonStaticPrivateMethod.");
    }

    public String getAnString() {
        return anString;
    }

    public int getAnInt() {
        return anInt;
    }

    public int getAnInt2() {
        return anInt2;
    }
}
