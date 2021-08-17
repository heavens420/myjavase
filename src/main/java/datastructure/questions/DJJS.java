package datastructure.questions;

import java.util.Arrays;

public class DJJS {

    public static void main(String[] args) {

        int[] array = new int[]{11, 99, 8, 1,99};
        int sum = test(array);
        System.out.println(sum);
    }

    public static int test(int[] array){
        int size = array.length;
        if (size == 0) {
            return 0;
        }
        int[] dp = new int[size+1];
        dp[0] = 0;
        dp[1] = array[0];
        for (int i = 2; i <= size; i++) {
            dp[i] = Math.max(dp[i - 1], array[i - 1] + dp[i - 2]);
        }
        System.out.println(Arrays.toString(dp));
        return dp[size];
    }
}
