package com.example.juc;

/**
 * @author wxl
 */
public class LambdaExpressDemo {
    public static void main(String[] args) {
//        Foo foo = () -> { System.out.println("comein"); };
        Foo foo = (a, b) -> {
            System.out.println("come in");
            return a + b;
        };
        System.out.println(foo.add(3, 3));
        System.out.println(foo.div(10, 5));
        System.out.println(Foo.my(11,2));

    }
}

//@FunctionalInterface
interface Foo {

    public int add(int a, int b);

    default int div(int x, int y) {
        System.out.println(" hello word");
        return x / y;
    }

    public static int my(int a, int b) {
        return a * b;
    }
}