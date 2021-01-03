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

//        int[] array = add(arr, size, 3, 999);
        int[] array = delete(arr, 2);
        System.out.println(Arrays.toString(array));
    }

    public static int[] expandArray(int[] array,int size){

        if (SIZE > size) {
            return array;
        }else {
//            int newSize = size + (size >> 1);
            int newSize = size + 1;
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

    public static int[] delete(int[] arr,int index){
        if (index > arr.length - 1 || index < 0){
            throw new RuntimeException("删除索引越界");
        }
        for (int i = index;i < arr.length - 1;i++){
            arr[i] = arr[i+1];
        }
//        arr[arr.length - 1] = 0;

        // 新建数组，删除则数组长度减一
        int[] arrNew = new int[arr.length - 1];
        //将原数组拷贝到新建数组
        System.arraycopy(arr,0,arrNew,0,arr.length - 1);
        return arrNew;
    }
}
