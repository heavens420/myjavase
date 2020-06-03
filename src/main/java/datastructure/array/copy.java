package datastructure.array;

import java.util.Arrays;

/**
 * 数组拷贝方法
 */
public class copy {
    public static void main(String[] args) {
        int[]arr = {1,2,3,4,5};
        int[]arr2 = new int[10];

        int[] arrCopy = Arrays.copyOfRange(arr,1,3);

        int[] arrCopy2 = Arrays.copyOf(arr, 8);

        System.arraycopy(arr,0,arr2,3,arr.length);

        System.out.println("arrCopy: "+Arrays.toString(arrCopy));
        System.out.println("arrCopy2: "+Arrays.toString(arrCopy2));
        System.out.println("arr2:"+Arrays.toString(arr2));
    }
}
