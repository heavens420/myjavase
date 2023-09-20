package com.zlx.java8features;

import javafx.util.Pair;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class OptionalTest {
    public static void main(String[] args) throws IOException {
//        test1();
//        test2();
//        test3();
//       test7();
//        test8();
        System.out.println(Arrays.toString("".split(",")));

    }

    public static void test1() {
        User user = new User(12, "sf");

//        Optional.ofNullable(user).ifPresent(stu -> System.out.println(stu.getName()));
        User user1 = null;
        User user2 = Optional.ofNullable(user1).orElse(null);
        System.out.println(user2);

        User user3 = Optional.ofNullable(user1).orElseGet(() -> user);
        System.out.println(user3);


    }

    public static void test2() {
        List<String> list = null;
        // list 不为空执行 否则不执行
        Optional.ofNullable(list).ifPresent(System.out::println);
    }

    public static void test3() {
        User user = new User(33, "as");
        // 第一个map 获取name 第二个map 对name(前一个map获取的值)进行操作
        String lisi = Optional.ofNullable(user).map(User::getName).map(y -> String.join(",", y, "lisi")).orElseGet(() -> "wangwu");
        System.out.println(lisi);
    }

    public static void test4() {
        User user = new User(90, "jaja");
        User result = Optional.ofNullable(user).filter(x -> x.getId() > 100).orElseGet(User::new);
        System.out.println(result);
    }

    public static void test5() {
        User user = new User(90, "jaja");
        String name = Optional.ofNullable(user).flatMap(x -> {
            if (x.getName().equals("wee")) {
                return Optional.of(x);
            } else {
                user.setName("lisi");
                return Optional.of(user);
            }
        }).flatMap(y -> {
            String asd = String.join(",", y.getName(), "sdf");
            y.setName(asd);
            return Optional.of(y);
        }).map(User::getName).orElse("wangwu");
        System.out.println(name);

    }

    public static void test6() {
        List<String> list = new ArrayList<String>();
        String s = list.stream().filter(x -> x.equals("a")).findFirst().orElseGet(() ->"b");
        Stream<String> a1 = list.stream().filter(x -> x.equals("a"));
        Optional<String> a = list.stream().filter(x -> x.equals("a")).findFirst();
        Optional<String> s1 = Optional.<String>of(null);
        String fsfd = s1.orElse("fsfd");

        System.out.println(fsfd);
    }

    public static void test7() {
        List<Pair> list = new ArrayList<>();
        Pair pair = new Pair(1, 2);
        list.add(pair);

    }

    public static void test8() throws IOException {
//        Path tempFile = Files.createTempFile("temp222222222222222222222222222222222", ".txt");
//        Path fileName = tempFile.getFileName();
//        String string = fileName.toString();
//        System.out.println(string);
//        System.out.println(fileName);
//        System.out.println(tempFile);

        DateTimeFormatter firstDayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-01");
        String firstDayOfMonth = firstDayFormatter.format(LocalDateTime.now());
        System.out.println(firstDayOfMonth);

    }
}
