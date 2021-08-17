package datastructure.sort2;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
//        xuanze();
//        maopao();
//        charu();
//        paixu();
        xier();
    }

    public static void paixu() {
        int[] array = new int[]{221, 5, 436, 784, 78, 7654, 32};
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            System.out.printf("第%d次排序后：%s\n", i + 1, Arrays.toString(array));
        }
    }

    public static void xuanze() {
        int[] array = new int[]{23, 454, 46542, 12, 3, 45, 6};

        for (int k = 0; k < array.length - 1; k++) {
            int minIndex = k; // 最小值的初始值 一定要与k有关系 不能初始为0  否则 每次循环 最小值索引都是0 都会和第一个已经排好序的元素比较
            int temp;
            for (int i = k + 1; i < array.length; i++) {
                if (array[minIndex] > array[i]) {
                    minIndex = i;
                }
            }

            temp = array[k];
            array[k] = array[minIndex];
            array[minIndex] = temp;
            System.out.printf("第%d趟排序后：%s\n", k + 1, Arrays.toString(array));
        }
    }

    public static void maopao() {
        int[] array = new int[]{23, 325, 45, 46, 45, 6, 432, 2, 3, 1};
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            System.out.printf("第%d次排序后：%s\n", i + 1, Arrays.toString(array));
        }
    }

//    public static void charu() {
//        int[] array = new int[]{2534, 234, 2, 1, 3, 5, 64, 56, 6};
//
//        for (int i = 0; i < array.length - 1; i++) {
//            for (int j = 1; j < array.length; j++) {
//                if (array[j - 1] > array[j]) {
//                    int temp = array[j];
//                    array[j] = array[j - 1];
//                    array[j - 1] = temp;
//                }
//            }
//            System.out.printf("第%d次排序后：%s\n", i + 1, Arrays.toString(array));
//        }
//    }

    public static void charu() {
        int[] array = new int[]{2, 32, 5, 63, 7, 45};
        for (int i = 1; i < array.length; i++) {
            int currentIndex = i;
            int current = array[i];
            while (current < array[currentIndex - 1]) {
                array[currentIndex] = array[currentIndex - 1];
                currentIndex--;
            }
            array[currentIndex] = current;
            System.out.printf("第%d次排序后：%s\n", i + 1, Arrays.toString(array));
        }
    }

    public static void xier() {
        int[] array = new int[]{5, 345, 34, 1, 45, 12, 33, 88, 56, 453, 55};

        int count = 0;
        // 先分组
        for (int step = array.length / 2; step > 0; step /= 2) {
//            分组之后  就是一个插入排序
            for (int i = step; i < array.length; i++) {
                int current = array[i];
                int currentIndex = i;

                while (currentIndex >= step && current < array[currentIndex - step]) {
                    array[currentIndex] = array[currentIndex - step];
                    currentIndex -= step;
                }
                array[currentIndex] = current;
            }
            System.out.printf("第%d次排序后：%s\n", ++count, Arrays.toString(array));
        }
    }

    public static void jiandan(){

    }
}
