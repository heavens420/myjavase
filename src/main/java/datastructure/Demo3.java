package datastructure;

import java.util.Arrays;

/**
 * 插入排序
 * 默认第一个数为有序数列
 * 后面的数依次和前面的有序数列比较
 * 后面的无序数列逐个插入前面的有序数列中组成新的有序数列
 */
public class Demo3 {
    public static void main(String[] args) {
        int[] array = {0, 2, 4, 1, 3, 7, 6, 5,};
        System.out.println("原数组:");
        System.out.println(Arrays.toString(array));
//        insertSort(array);
        sSort(array);
//        insertSort2(array);
//        sort(array);
    }


    public static void insertSort(int[] array) {
        int insertIndex;
        int insertValue;
        for (int i = 1; i < array.length; i++) {
            //待插入的索引位置 (待插入数的索引为 i 所以 要插入到 i-1的位置)
            insertIndex = i - 1;
            //待插入的数
            insertValue = array[i];
            //防止插入的下标值越界&& 当待插入的数值小于要比较的值时 被插入的值向后移动 腾出位子给 新值插入
            while (insertIndex >= 0 && insertValue < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                //待插入数与前面的有序序列比较时 从有序数列的右边(最大值方向)往左比较 若找不到插入位置 则向左继续比较 故 插入索引 要减减
                insertIndex--;
            }
            //while循环中 insertIndex--(insert Index 本身就是要插入的位置) 多减了 1 故要加 1
            if (insertIndex + 1 != i) {
                array[insertIndex + 1] = insertValue;
            } else {
                System.out.println("===");
            }
            System.out.println("第" + (i) + "轮排序后:");
            System.out.println(Arrays.toString(array));
        }
    }


    //lianxi
    public static void sSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insertIndex = i - 1;
            int insertValue = array[i];
            while (insertIndex >= 0 && insertValue < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            array[insertIndex + 1] = insertValue;

            System.out.println(Arrays.toString(array));
        }
    }

    //错误演示
    public static void insertSort2(int[] array) {
        int temp = 0;
        for (int i = 1; i < array.length; i++) {
            temp = array[i];
            int k = i;
            while (k > 0 && temp < array[k]) {
                array[k] = array[k - 1];
                k--;
            }
            array[k] = temp;
            System.out.println("第" + (i) + "轮排序后:");
            System.out.println(Arrays.toString(array));
        }
    }

    public static void sort(int[] arr) {
        int tmp = 0;

        for (int i = 1; i < arr.length; i++) {
            tmp = arr[i];
            int j = i;
            while (j > 0 && arr[j] >= tmp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = tmp;
            System.out.println("第" + (i) + "轮排序后:");
            System.out.println(Arrays.toString(arr));
        }
    }
}
