package datastructure;

import java.util.Arrays;

/**
 * 希尔排序(交换式 不推荐 效率很低)
 * 先分组（array.length/2)，每组两个数据 每组之间排序
 * 每组排序完成后 重新分组(array.length/2/2),每组之间再次排序
 * 不断重复直到
 */
public class Demo4 {
    public static void main(String[] args) {
//        int[] array = new int[]{1, 5, 3, 9, 54, 23, 90, 11, 23, 77, 8};
//        shellSort(array);

        int[] array = new int[80000];
        for (int i = 0; i < 80000; i++) {
            array[i] = i;
        }
        long start = System.currentTimeMillis();
        test(array);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }


    public static void shellSort(@org.jetbrains.annotations.NotNull int[] array) {
        int temp;
        int count = 0;
        //每次步长为原来的1/2 两两分组  控制排序趟数
        for (int step = array.length / 2; step > 0; step /= 2) {
            //每组依次进行比较和交换  共Math.ceil(step)组 需循环这么多次
            for (int i = step; i < array.length; i++) {
                //比较交换 遍历每组中的元素
                for (int j = i - step; j >= 0; j -= step) {
                    if (array[j + step] < array[j]) {
                        temp = array[j + step];
                        array[j + step] = array[j];
                        array[j] = temp;
                    }
                }
            }
            System.out.println("第" + (++count) + "趟排序后:");
            System.out.println(Arrays.toString(array));
        }
    }

    //练习
    public static void test(int[] array) {
        int temp;
        for (int step = array.length / 2; step > 0; step /= 2) {
            for (int i = step; i < array.length; i++) {
                for (int j = i - step; j >= 0; j -= step) {
                    if (array[j] > array[j + step]) {
                        temp = array[j];
                        array[j] = array[j + step];
                        array[j + step] = temp;
                    }
                }
            }
//            System.out.println(Arrays.toString(array));
        }
    }
}
