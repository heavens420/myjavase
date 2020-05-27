package datastructure.sort;

import java.util.Arrays;

/**
 * 插入排序
 */

public class three {
    public static void main(String[] args) {
        int[]arr = {2,32,7,3,1,5,4};
        //此方法为两两比较交换方式
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1;j<arr.length;j++){
                if (arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        Arrays.stream(arr).forEach(value -> System.out.print(value+"\t"));
    }


}
