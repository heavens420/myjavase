package datastructure.questions;

import java.util.Arrays;

public class 最小堆 {
    public static void main(String[] args) {
        int[] array = new int[]{3, 25, 3, 675, 3, 23, 68, 9};
        final int[] samllestK = samllestK(array, 3);
        System.out.println(Arrays.toString(samllestK));

    }

    public static int[] samllestK(int[] arr, int k) {
        int[] samllest = new int[k];
        int minIndex = -1;
        for (int j = 0; j < k; j++) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < arr.length; i++) {
                if (min > arr[i]) {
                    min = arr[i];
                    minIndex = i;
                }
            }
            if (minIndex != -1) {
                arr[minIndex] = Integer.MAX_VALUE;
            }
            samllest[j] = min;
        }
        return samllest;
    }
}
