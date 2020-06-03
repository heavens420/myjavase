package datastructure.array;

import java.util.Arrays;

/**
 * 数组扩容
 */
public class expand {
    public static final int SIZE = 4; //数组初始容量

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,12};
        int size = arr.length;

        int[] array = add(arr, size, 3, 999);
        System.out.println(Arrays.toString(array));
    }

    public static int[] expandArray(int[] array,int size){

        if (SIZE > size) {
            return array;
        }else {
            int newSize = size + (size >> 1);
            int[] newArray = new int[newSize];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            return newArray;
        }
    }

    public static int[] add(int[] arr,int s,int index,int value){
        if (index > s){
            throw new RuntimeException("插入的索引越界");
        }
        //容量不够则扩容 否则不扩容
        int[] array = expandArray(arr, s);
        int size = array.length;

        for (int i = size-1; i > index; i--) {
            array[i] = array[i-1];
        }
        array[index] = value;
        return array;
    }
}
