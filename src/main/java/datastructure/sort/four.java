package datastructure.sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class four {
    public static void main(String[] args) {
        int[] arr = {33,22, 5, 8, 1, 4, 8, 9, 4, 3, 2};

        //优化step取值
        int h =1;
        while (h <=arr.length/3){
            h = h*3 +1;
        }
        for (int step = h; step > 0; step = (step)/3) {
            for (int i = step-1; i < arr.length; i++) {
                for (int j = i; j <arr.length; j += step) {
                    if (arr[j] < arr[i]) {
                        int temp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = temp;
                    }
                }
            }
        }
        Arrays.stream(arr).forEach(value -> System.out.print(value + "\t"));
    }
}
