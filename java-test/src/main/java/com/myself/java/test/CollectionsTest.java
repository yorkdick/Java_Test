package com.myself.java.test;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class CollectionsTest {

    public static void main(String[] args) {
        String[] arr = new String[]{"1","2","3"};
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        System.out.println(arr);
        System.out.println(list);
    }

    public static void listTest(){
        List<String> strings  = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");

        strings.forEach(s -> System.out.println(s));

        List<String> strings2  = new LinkedList<>();
        strings2.add("1");
        strings2.add("2");
        strings2.add("3");
        strings2.remove("3");

        strings2.forEach(s -> System.out.println(s));
    }

    public static void mapTest(){
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("key22","2");
        stringMap.put("key1","1");
        stringMap.put("key3","3");
        stringMap.keySet().forEach(key -> System.out.println(key));

        Map<String,String> stringMap1 = new LinkedHashMap<>();
        stringMap1.put("key2","2");
        stringMap1.put("key1","1");
        stringMap1.put("key3","3");
        stringMap1.keySet().forEach(key -> System.out.println(key));

        Map<String,String> stringMap2 = new TreeMap<>((a,b) -> b.compareTo(a));
        stringMap2.put("key2","2");
        stringMap2.put("key1","1");
        stringMap2.put("key3","3");
        stringMap2.keySet().forEach(key -> System.out.println(key));


    }

    public static void setTest(){
        Set<String> stringSet = new HashSet<>();
        stringSet.add("key2");
        stringSet.add("key1");
        stringSet.add("key3");
        stringSet.forEach(key -> System.out.println(key));

        Set<String> stringSet1 = new LinkedHashSet<>();
        stringSet1.add("key2");
        stringSet1.add("key1");
        stringSet1.add("key3");
        stringSet1.forEach(key -> System.out.println(key));

        Set<String> stringSet2 = new TreeSet<>(String::compareTo);
        stringSet2.add("key2");
        stringSet2.add("key1");
        stringSet2.add("key3");
        stringSet2.forEach(key -> System.out.println(key));
    }

    public static void stackTest(){
        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        stack.forEach(key -> System.out.println(key));

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        stack.forEach(key -> System.out.println(key));

    }

    public static void queueTest(){
        Queue<String> strings = new LinkedBlockingDeque<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");

        strings.forEach(key -> System.out.println(key));
        System.out.println(strings.poll());
        System.out.println(strings.poll());
        System.out.println(strings.poll());
        strings.forEach(key -> System.out.println(key));

    }
}
