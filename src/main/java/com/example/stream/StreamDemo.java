package com.example.stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

/**
 * @author wxl
 * <p>
 * 请按照给出数据，找出同时满足一下条件的用户，也即一下条件全部满足
 * 偶数id切年龄大于24  切用户名转为大写 且用户名字母降序
 * 只输出一个用户的名字
 */
public class StreamDemo {
    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);
        List<User> users = Arrays.asList(u1, u2, u3, u4, u5);

        users.stream()
                .filter(user -> user.getId() % 2 == 0 && user.getAge() > 24)
                .map(m -> m.getUserName().toUpperCase())
                .sorted((x, y) -> y.compareTo(x))
                .limit(1)
                .forEach(System.out::println);


       /* //函数型
        Function<String, Integer> function = s -> {
            return s.length();
        };
        System.out.println(function.apply("abc"));

        //断定型
      *//*  Predicate<String> predicate=new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        };*//*
        Predicate<String> predicate = s -> {
            return s.isEmpty();
        };
        System.out.println(predicate.test("we"));
        //消费性
      *//*  Consumer<String>  consumer=new Consumer<String>() {
            @Override
            public void accept(String s) {

            }
        };*//*

        Consumer<String> consumer = s -> {
            System.out.println("s");
        };
        consumer.accept("sdf");
        //供给型
        *//*Supplier supplier=new Supplier() {
            @Override
            public Object get() {
                return null;
            }
        };*//*
        Supplier<String> supplier = () -> {
            return "ajva";
        };
        System.out.println(supplier.get());*/
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
class User {
    private Integer id;
    private String userName;
    private Integer age;
}