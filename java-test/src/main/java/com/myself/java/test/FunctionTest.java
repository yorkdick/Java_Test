package com.myself.java.test;

import java.util.UUID;
import java.util.function.*;

public class FunctionTest {
    public static void main(String[] args) {
        Predicate<String> predicate = s -> "aaa".equals(s);
        System.out.println(predicate.test("aaa"));

        BiPredicate<String, Integer> biPredicate = (a, b) -> Integer.valueOf(a) > b;
        System.out.println(biPredicate.test("4", 3));

        Consumer<String> consumer = (s) -> System.out.println(s);
        consumer.accept("4");

        BiConsumer<String, Integer> biConsumer = (a, b) -> System.out.println(a + " " + b.toString());
        biConsumer.accept("1", 4);

        Supplier<String> stringSupplier = () -> UUID.randomUUID().toString();
        System.out.println(stringSupplier.get());

        Function<Integer, Integer> function = x -> x + 1;
        System.out.println(function.apply(1));

        BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> x + y;
        System.out.println(biFunction.apply(1, 2));

        System.out.println(convertS("aaa", (a, b) -> a + "1"));
    }

    public static void consumer(String a, Consumer<String> consumer) {
        consumer.accept(a);
    }

    public static String getString(Supplier<String> stringSupplier) {
        return stringSupplier.get();
    }

    public static String convertS(String a, FunctionInterface functionInterface) {
        return functionInterface.convert(a, a);
    }
}
