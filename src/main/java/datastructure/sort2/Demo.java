package datastructure.sort2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 排序算法
 */
public class Demo {
    public static void main(String[] args) {
        int[] arr2 = new int[3];
        int[] arr = new int[]{23,4,56,12,55,90};
        int[] arr3 = {};

//        maopao(arr);

        Scanner s = new Scanner(System.in);
        while(s.hasNextInt()) {
            int count = s.nextInt();
            int sum = 0;
            while(count-- > 0) {
                sum += s.nextInt();
            }
            System.out.println(sum);
        }

    }

    /**
     * 冒泡排序
     * @param arr
     */
    public static void maopao(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++){
                if (arr[j] > arr[j+1]){
                    int temp = 0;
                    temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void xuanze(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            for (int j = i; j < arr.length - 1; j++){
                if (arr[j] > arr[j+1]){

                }
            }
        }
    }
}
