package com.springboott.ttdemo.test;

import com.springboott.ttdemo.po.User;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaText {
    public static void main(String[] args) {
        System.out.println("Lambda之美 https://mp.weixin.qq.com/s/JAtbtMJmjG3njOGp3Az2-g");
        Predicate<Integer> predicate = x -> x > 15;
        User us = new User(1, "李云龙", 25);
        System.out.println("李云龙的年龄是否大于15岁 " + predicate.test(us.getAge()));

        Consumer<String> consumer = System.out::println;
        consumer.accept("命运由我不由天");

        Function<User, String> function = User::getUsername;
        String name = function.apply(us);
        System.out.println("#### 对象属性显示 ####");
        System.out.println("name = " + name);

        Supplier<Integer> supplier = () -> Integer.valueOf(BigDecimal.TEN.toString());
        System.out.println(supplier.get());

        UnaryOperator<Boolean> unaryOperator = uglily -> !uglily;
        Boolean apply2 = unaryOperator.apply(true);
        System.out.println(apply2);

        BinaryOperator<Integer> operator = (x, y) -> x * y;
        Integer integer = operator.apply(2, 3);
        System.out.println("#### 算数 ####");
        System.out.println("2*3 = " + integer);

        test(() -> "我是一个演示的函数式接口");

        //常用流 + filter
        List<User> userlist1 = Stream.of(
                new User(1, "李云龙", 25, "11"),
                new User(2, "赵子龙", 22, "12"),
                new User(3, "李世伟", 15, "11"),
                new User(4, "林子涵", 80, "13")
        ).filter(user -> user.getAge() < 50).collect(Collectors.toList());
        System.out.println("#### 常用流 userlist1 ####");
        userlist1.forEach(System.out::println);
        boolean matchAge = userlist1.stream().anyMatch(user -> user.getAge() == 25);
        System.out.println("是否存在一个 user 对象的 age 等于 25 = " + matchAge);

        //filter用法
        List<User> userlist2 = new ArrayList<>();
        userlist2.add(new User(1, "李云龙", 25, "11"));
        userlist2.add(new User(2, "赵子龙", 22, "12"));
        userlist2.add(new User(3, "李世伟", 15, "11"));
        userlist2.add(new User(4, "林子涵", 80, "13"));
        userlist2.add(new User(5, "喵喵喵", 68, "13"));
        userlist2.add(new User(6, "李丹妮", 8, "13"));

        List<User> userlist3 = userlist2.stream()
                .filter(user -> user.getAge() < 50)
                .collect(Collectors.toList());
        System.out.println("#### filter userlist3 ####");
        userlist3.forEach(System.out::println);

        //map 用法
        List<String> userlist4 = userlist2.stream().map(user -> user.getUsername())
                .collect(Collectors.toList());
        System.out.println("#### map userlist4 ####");
        userlist4.forEach(System.out::println);

        //flatMap 将多个Stream合并为一个Stream。惰性求值
        List<User> userlist5 = Stream.of(userlist2, Arrays.asList(
                new User(4, "狐狸", 16),
                new User(5, "雷利", 48)
        )).flatMap(user -> user.stream()).collect(Collectors.toList());
        System.out.println("#### flatMap userlist5 ####");
        userlist5.forEach(System.out::println);

        //User对象指定元素最大/最小 列值 max和min
        Optional<User> max = userlist2.stream()
                .max(Comparator.comparing(user -> user.getAge()));
        Optional<User> min = userlist2.stream()
                .min(Comparator.comparing(user -> user.getAge()));
        System.out.println("#### max and min ####");
        //判断是否有值
        if (max.isPresent()) {
            System.out.println(max.get());
        }
        if (min.isPresent()) {
            System.out.println(min.get());
        }

        //统计功能 count
        long count = userlist2.stream().filter(user -> user.getAge() < 45).count();
        System.out.println("#### 统计 count #### \n年龄小于45岁的人数是：" + count);

        //reduce 操作可以实现从一组值中生成一个值  累加器
        Integer reduce = Stream.of(1, 2, 3, 4, 5, 6).reduce(0, (acc, x) -> acc + x);
        System.out.println("#### 累加器 reduce #### \n" + reduce);

        //取平均值 Collectors.averagingInt
        List<Integer> userlist6 = userlist2.stream().map(user -> user.getAge()).collect(Collectors.toList());
        Double averageAge = userlist2.stream().collect(Collectors.averagingInt(User::getAge));
        System.out.println("#### age平均值参数列 userlist6 ####");
        userlist6.forEach(System.out::println);
        System.out.println("age平均值 = " + averageAge);

        //根据字段指定内容分组 Collectors.partitioningBy
        Map<Boolean, List<User>> listMap1 = userlist2.stream().collect(
                Collectors.partitioningBy(user -> user.getTelephone().
                        contains("11")));
        System.out.println("#### 根据字段指定内容分组 listMap1 ####");
        System.out.println("listMap = " + listMap1);

        //根据字段分组 groupingBy  像sql groupby
        Map<String, List<User>> listMap2 = userlist2.stream()
                .collect(Collectors.groupingBy(user -> user.getTelephone()));
        System.out.println("#### 字段分组 listMap2 ####");
        System.out.println("listMap = " + listMap2);

        //字符串拼接 Collectors.joining()
        String names = userlist2.stream()
                .map(User::getUsername).collect(Collectors.joining("==", "[", "]"));
        System.out.println("#### 字符串拼接 ####");
        System.out.println("names = " + names);


    }

    /**
     * 演示自定义函数式接口使用
     *
     * @param worker
     */
    public static void test(Worker worker) {
        String work = worker.work();
        System.out.println("work = " + work);
    }

    public interface Worker {
        String work();
    }

    @Test
    public void stream() {
        Pattern pattern = Pattern.compile(",");
        Stream<String> stringStream = pattern.splitAsStream("a,b,c,d");
        stringStream.forEach(System.out::print);
        System.out.println();

        Stream<Integer> stream = Stream.of(6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14);
        Stream<Integer> newStream = stream.filter(s -> s > 5) //6 6 7 9 8 10 12 14 14 过滤流中的某些元素
                .distinct() //6 7 9 8 10 12 14  通过流中元素的 hashCode() 和 equals() 去除重复元素
                .skip(2) //9 8 10 12 14  跳过n元素，配合limit(n)可实现分页
                .limit(2); //9 8  获取n个元素
        newStream.forEach(System.out::println);

        Integer findFirst = stream.findFirst().get();


        List<User> userlist1 = Stream.of(
                new User(1, "李云龙", 25, "11"),
                new User(2, "赵子龙", 22, "12"),
                new User(3, "李世伟", 15, "11"),
                new User(4, "林子涵", 80, "13")
        ).filter(user -> user.getAge() < 50).collect(Collectors.toList());
        System.out.println("#### 常用流 userlist1 ####");
        //消费peek：如同于map，能得到流中的每一个元素。但map接收的是一个Function表达式，有返回值；而peek接收的是Consumer表达式，没有返回值。
        userlist1.stream()
                .peek(o -> o.setAge(100)) //统一set
                .forEach(System.out::println);

    }

}
