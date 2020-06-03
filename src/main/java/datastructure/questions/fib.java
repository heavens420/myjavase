package datastructure.questions;

/**
 * 斐波那契数列 1 1 2 3 5 8
 */

public class fib {
    public static void main(String[] args) {
        long num = 3;
        System.out.println(getFib(num));
        System.out.println(getFib2(num));
    }

    /**
     * 递归方式
     *
     * @param num
     * @return
     */
    public static long getFib(long num) {
        if (num <= 2) {
            return 1;
        } else {
            return getFib(num - 1) + getFib(num - 2);
        }
    }

    /**
     * 循环方式
     * @param num
     * @return
     */
    public static long getFib2(long num){
        if (num <= 2){
            return 1;
        }
        int first = 1;
        int second = 1;
        int sum = 0;
        for (int i = 0; i < num - 2; i++) {
            sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }
}
