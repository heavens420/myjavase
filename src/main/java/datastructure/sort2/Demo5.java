package datastructure.sort2;

import java.util.Arrays;

/**
 * 希尔排序的 移位式（推荐 效率高）
 */
public class Demo5 {
    public static void main(String[] args) {
//        int[] array = {34, 65, 2, 5, 6, 76, 3, 7, 534, 33};
        int[] array = new int[80000];
        for (int i = 0; i < 80000; i++) {
            array[i] = i;
        }

        long start = System.currentTimeMillis();
        shellSort(array);
        long end = System.currentTimeMillis();
        System.out.println("耗时: "+(end -start)+" 毫秒");
    }

    public static void shellSort(int[] array) {
        for (int step = array.length / 2; step > 0; step /= 2) {
            for (int i = step; i < array.length; i++) {
                for (int j = i; j >= 0; j -= step) {
                    int insertIndex = j - step;
                    int insertValue = array[j];
                    while (insertIndex >= 0 && insertValue < array[insertIndex]) {
                        array[step + insertIndex] = array[insertIndex];
                        insertIndex -= step;
                    }
                    array[step + insertIndex] = insertValue;
                }
            }
//            System.out.println(Arrays.toString(array));
        }
    }

}
