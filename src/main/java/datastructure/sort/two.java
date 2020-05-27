package datastructure.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class two {
    public static void main(String[] args) {
        int[]arr = {2,8,1,4,7,2};
        for (int i = 0;i<arr.length;i++){
            int k = i; //用于记录最小下表索引
            for (int j = k+1;j<arr.length;j++){
                if (arr[j] < arr[k]){
                    k = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
        }
        Arrays.stream(arr).forEach(value -> System.out.print(value+"\t"));
    }
}
