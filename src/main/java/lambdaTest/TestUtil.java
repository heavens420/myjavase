package lambdaTest;

import liquibase.pro.packaged.S;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestUtil {


    public static void main(String[] args) {
//        testFunction();
//        testArgs(2, 3);
//        testPredicate();
//        testIfCondition();
//        testIfCondition2();
        testConsumerWhile();
    }

    public static void testConsumer() {
        List<String> list = new ArrayList<>();
        Util.testConsumer((x) -> {
            String[] split = x.split(",");
            for (String s : split) {
                list.add(s + "-co");
            }
        }, (y) -> {
            List<String> collect = list.stream().filter(y::equals).collect(Collectors.toList());
            System.out.println("collect:" + collect);
        }, "q,e,rt,", "q-co");
        System.out.println(list);
    }

    public static void testSupplier() {
        String result = Util.testSupplier(() -> {
            List<String> list = Arrays.asList("Hello", "World");
            return String.join("--", list);
        });
        System.out.println(result);
    }

    public static void testFunction() {
        Integer result = Util.testFunction(x -> {
            String[] split = x.split(",");
            for (String s : split) {
                if (s.equals("e")) {
                    return 3;
                }
            }
            return -1;
        }, "a,b,c,d,e,f,g,h");
        System.out.println("result:" + result);
    }


    public static void testBiFunction() {
        String result = Util.testBiFunction((x, y) -> {
            return x + y;
        }, 1, "3");
        System.out.println("result:" + result);
    }

    public static void testPredicate() {
        boolean result = Util.testPredicate(x -> x.equals(1), 1L);
        System.out.println("result:" + result);
    }

    public static void testArgs(int a, int b) {
        int sum = Util.testArgs(true, (x, y) -> a * b, (x, y) -> a + b);
        System.out.println(sum);
    }

    public static void testIfCondition() {
        Util.ifCondition(true, () -> {
            System.out.println("if");
            return "if";
        }, () -> {
            System.out.println("else");
            return "else";
        });
    }

    public static void testIfCondition2() {
        Util.ifCondition2(true,
                (x) -> {
                    System.out.println("if");
                },
                (x) -> {
                    System.out.println("else");
                });
    }

    public static void testConsumerWhile() {
        Util.testConsumer(x -> {
            System.out.println("x=" + x);
        });
    }
}
