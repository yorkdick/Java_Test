package com.myself.java.test;

public class GemericTest2 {
    static class Plate<T>{
        private T item;
        public Plate(T t){item=t;}
        public void set(T t){item=t;}
        public T get(){return item;}
    }

    static class Plate2<T extends Fruit>{
        private T item;
        public Plate2(T t){item=t;}
        public void set(T t){item=t;}
        public T get(){return item;}
    }

    //Lev 1
    static class Food{public void food(){}}
    //Lev 2
    static class Fruit extends Food{public void fruit(){}}
    static class Meat extends Food{public void meat(){}}
    static class Apple extends Fruit {public void apple(){}}
    static class Banana extends Fruit{public void banana(){}}
    static class Pork extends Meat{public void pork(){}}
    static class Beef extends Meat{public void beef(){}}

    public static void main(String[] args) {

        Plate2<Fruit> a = new Plate2<Fruit>(new Apple());

        Plate<Fruit> fruitPlate = new Plate<Fruit>(new Apple());
        fruitPlate.set(new Banana());
        fruitPlate.get().fruit();
        ((Apple)fruitPlate.get()).apple();

        Plate<? extends Fruit> p=new Plate<>(new Apple());
//不能存入任何元素
//        p.set(new Fruit());    //Error
//        p.set(new Apple());
         Fruit fruit = p.get();

//读取出来的东西只能存放在Fruit或它的基类里。
        Fruit newFruit1=p.get();
        Object newFruit2=p.get();
//        Apple newFruit3=p.get();


        Plate<? super Fruit> p2 =new Plate<>(new Apple());
        Plate<? super Fruit> p3 =new Plate<>(new Food());
        Plate<? super Fruit> p4 =new Plate<>(new Object());
        Plate<? super Fruit> p5 =new Plate<>(new Meat());
        Plate<? super Fruit> p6 =new Plate<>(new String(""));

//存入元素正常
        p2.set(new Fruit());
        p2.set(new Apple());

//读取出来的东西只能存放在Object类里。
//        Apple newFruit31=p2.get();    //Error
//        Fruit newFruit11=p2.get();    //Error
        Object newFruit21=p2.get();
    }
}
