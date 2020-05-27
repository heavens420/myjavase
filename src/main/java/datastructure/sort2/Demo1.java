package datastructure.sort2;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;

/*
    冒泡排序：
    每一趟将最小的或最大的数依次找出来放在第一位或最后一位
 */
public class Demo1 {
    public static void main(String[] args) throws IOException {
        int[] array = {23, 564, 757, -432, 534, 63, -4, 0};
        System.out.print("原数组： ");
        System.out.println(Arrays.toString(array));
        System.out.println();

        //方式一  从左往右排 先排小
        //maopao(array);

        //方式二  从右往左排 先排大
        maopao2(array);

        //优化后的冒泡排序
        maopao3(array);
    }

    public static void maopao(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int temp = 0;
            for (int j = array.length - 1; i < j; j--) {
                if (array[j] < array[j - 1]) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "趟排序后：");
            System.out.println(Arrays.toString(array));
        }
    }

    public static void maopao2(int[] array) {
        //定义临时变量用于 数据交换
        int temp;
        //外层循环控制 总的遍历(需要排几次序)次数（数组长度 - 1 ）
        for (int i = 0; i < array.length - 1; i++) {
            //内层循环控制 排序(比较大小 升序排列 不满足就交换)
            //第一趟 i = 0 即 n 个数 要比较 n-1次(左右相邻 两两比较)
            //第二趟 i = 1 第一趟已经排好了一个数(升序时 冒泡到最右边 降序反之) 即 还剩 n-1 个数没有排序 需要比较 n-1 -1次( n-1-i)
            //第三趟 i = 2 第二趟之后 已经排好两个数 此时剩下 n - 2 个数 需要比较 n-1 - 2次(n-1-i)
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "趟排序后：");
            System.out.println(Arrays.toString(array));
        }
    }

    //优化冒泡算法 : 若数组元素已经有序 排序提前结束
    public static void maopao3(int[] array) {
        int temp;
        //是否有交换的标志 false 代表无交换
        boolean flag = false;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    //满足交换条件 标志 变为true
                    flag = true;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "趟排序后：");
            System.out.println(Arrays.toString(array));

            //判断是否出现交换
            if (!flag){
                //未交换 提前结束排序
                break;
            }else {
                //出现了交换 排序继续
                flag = false;
            }
        }
    }

}
