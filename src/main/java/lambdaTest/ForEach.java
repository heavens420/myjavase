package lambdaTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * lambda foreach遍历集合
 */

public class ForEach {
    public static void main(String[] args) throws IOException {

        List<String> list = Arrays.asList("ni","hao","az ne","zhe","shi","jie","assss");
        //将以a开头的元素筛选出来并转换为大写并按照字母表排序
        List<String> newList = list.stream().filter(s -> s.startsWith("a")).map(String::toUpperCase).sorted().collect(Collectors.toList());
        System.out.println(newList);

        String[] array = {"a","d","z","n","l","ad","aa"};
        //转化为String
        String ss = Stream.of(array).filter(s -> s.startsWith("a")).map(String::toUpperCase).sorted().collect(Collectors.joining());
        System.out.println(ss);

        //将文件转化为流的形式 并打印输出
        Stream<String> stream = Files.lines(Paths.get("C:\\Workplaces\\java\\w2\\myjavase\\src\\main\\java\\lambdaTest\\LambdaExpression.java"));
        stream.filter(s -> !s.isEmpty()).forEach(s -> System.out.println(s));
    }
}
