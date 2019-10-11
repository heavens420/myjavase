package com.zlx.test;


//        选择排序（Selection sort）是一种简单直观的排序算法。
//        它的工作原理是：第一次从待排序的数据元素中选出最小（或最大）的一个元素，
//        存放在序列的起始位置，然后再从剩余的未排序元素中寻找到最小（大）元素，
//        然后放到已排序的序列的末尾。以此类推，直到全部待排序的数据元素的个数为零。
//        选择排序是不稳定的排序方法。

public class Demo2 {
    public static void main(String[] args) {
        int[] array = {23,564,757,-432,534,63,-4,534,54,36,535,654,};
        System.out.print("原数组： ");
        for (int num:array
        ) {
            System.out.print(num+" ");
        }
        System.out.println();
        System.out.print("排序后： ");
        xuanze(array);
        for (int num:array
        ) {
            System.out.print(num+" ");
        }
    }

    public static void xuanze(int[] array){
        int k =0;
        int temp = 0;
        for (int i=0;i<array.length-1;i++){
            k = i;
            for (int j= k;j<array.length;j++){
                if (array[j] < array[k]){
                    k = j;
                }
            }
            temp = array[i];
            array[i] = array[k];
            array[k] = temp;
        }
    }
}
