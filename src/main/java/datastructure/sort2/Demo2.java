package datastructure.sort2;


//        选择排序（Selection sort）是一种简单直观的排序算法。
//        它的工作原理是：第一次从待排序的数据元素中选出最小（或最大）的一个元素，
//        存放在序列的起始位置，然后再从剩余的未排序元素中寻找到最小（大）元素，
//        然后放到已排序的序列的末尾。以此类推，直到全部待排序的数据元素的个数为零。
//        选择排序是不稳定的排序方法。

import redis.clients.jedis.Jedis;

import java.util.Arrays;

public class Demo2 {
    public static void main(String[] args) {
        int[] array = {2, 6, 1, 7, 9, 5};
//        int[] array = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            array[i] = (int)Math.random()*99999+1;
//        }
        System.out.print("原数组： ");
        System.out.println(Arrays.toString(array));
        System.out.println();

        long start = System.currentTimeMillis();
//        xuanze(array);
//        selectSort(array);
        test(array);
//        test2(array);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void xuanze(int[] array) {
        int k = 0;
        int temp = 0;
        for (int i = 0; i < array.length - 1; i++) {
            k = i;
            //获取最小值的下标(找到最小值)
            for (int j = k + 1; j < array.length; j++) {
                if (array[j] < array[k]) {
                    k = j;
                }
            }
            //将当前最小值与找到的新的最小值交换
            temp = array[i];
            array[i] = array[k];
            array[k] = temp;

            System.out.println("第" + (i + 1) + "趟排序后：");
            System.out.println(Arrays.toString(array));
        }
    }

    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            //假定的最小值下标
            int minIndex = i;
            //假定的最小值
            int min = array[i];
            //寻找最小值下标
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    //找到最小值下标 j 赋值给假定最小值下标
                    minIndex = j;
                    //将当前找到的最小值赋值给之前假定的最小值(保存找到的最小值)
                    min = array[j];
                }
            }
            //交换之前假定最小值与当前找到的最小值
            if (minIndex != i) {//若假定最小值就是最小值 没必要交换
                //将之前假定最小值赋值给当前找到的最小值
                array[minIndex] = array[i];
                //将当前找到的最小值 赋值给 假定最小值
                array[i] = min;
            }

            System.out.println("第" + (i + 1) + "趟排序后：");
            System.out.println(Arrays.toString(array));
        }
    }

    //练习
    public static void test(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int temp;
            int k = i;
            for (int j = k + 1; j < arr.length; j++) {
                if (arr[j] < arr[k]) {
                    k = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;

            System.out.println("第" + (i + 1) + "趟排序后：");
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void test2(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            int min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    minIndex = j;
                    min = array[j];
                }
            }
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = min;
            }
//            System.out.println("第" + (i + 1) + "趟排序后：");
//            System.out.println(Arrays.toString(array));
        }
    }
}
