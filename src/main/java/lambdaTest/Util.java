package lambdaTest;

import liquibase.pro.packaged.S;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.*;

@Slf4j
public class Util {
    public static <T> T testSupplier(Supplier<T> supplier) {
        System.out.println("执行前置操作");
        T result = supplier.get();
        System.out.println("执行后置操作");
        return result;
    }

    public static <T> void testConsumer(Consumer<T> consumer, Consumer<T> tConsumer, T t, T t2) {
        System.out.println("执行前置操作");
        if (t != null) {
            consumer.accept(t);
        }
        if (tConsumer != null) {
            // 相当于consumer.accept(t2);tConsumer.accept(t)
//            consumer.andThen(tConsumer).accept(t2);
            tConsumer.accept(t2);
        }
        System.out.println("执行后置操作");
    }

    public static <T> void testConsumer(Consumer<T> consumer, T t) {
        System.out.println("执行前置操作");
        if (t != null) {
            consumer.accept(t);
        }
        System.out.println("执行后置操作");
    }

    public static <T, R> R testFunction(Function<T, R> function, T t) {
        if (function == null) {
            return null;
        }
        return function.apply(t);
    }

    public static <T, R, V> V testFunctionCompose(Function<R, V> function, Function<T, R> function2, T t) {
        if (function == null) {
            return null;
        }
        return function.compose(function2).apply(t);
    }

    public static <T, U, R> R testBiFunction(BiFunction<T, U, R> function, T t, U u) {
        if (function == null) {
            return null;
        }
        return function.apply(t, u);
    }

    public static <T> T testBinaryOperator(T t, T t2) {
        Comparator<T> comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.toString().compareTo(o2.toString());
            }
        };
        BinaryOperator<T> stringBinaryOperator = BinaryOperator.minBy(comparator);
        return stringBinaryOperator.apply(t, t2);
    }

    /**
     * 比较对象是否相同 同时比对类型是否一致
     *
     * @param predicate
     * @param t
     * @param <T>
     * @return
     */
    public static <T> boolean testPredicate(Predicate<T> predicate, T t) {
        return predicate.test(t);
    }


    /**
     * 如果入参是一个接口且接口中只有一个方法，则传参可以为lambda表达式，若接口中方法需要入参，则入参在调用时传入，在此方法传入的无效。
     *
     * @param condition
     * @param args
     * @return
     */
    public static int testArgs(boolean condition, interface1... args) {
        if (condition) {
            int sum = 0;
            for (interface1 arg : args) {
                sum += arg.a(51, 61);
            }
            return sum;
        }
        return -1;
    }

    public static <T> void ifCondition(boolean condition, Supplier<T> supplier, Supplier<T> supplier2) {
        if (condition) {
            supplier.get();
        } else {
            supplier2.get();
        }
    }

    public static <T> void ifCondition2(boolean condition, Consumer<T> consumer, Consumer<T> consumer2) {
        if (condition) {
            consumer.accept(null);
        } else {
            consumer2.accept(null);
        }
    }

    public static void testConsumer(Consumer<String> consumer) {
        try {
            log.info("begin---------------");
            while (true) {
                log.info("do------------------------");
                TimeUnit.SECONDS.sleep(2);
                // 测试在accept里面抛异常 while执行情况
                consumer.accept("123");
                log.info("end---------------");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public static void testBreak(Consumer<String> consumer) {
//        consumer.accept();
//    }
}
