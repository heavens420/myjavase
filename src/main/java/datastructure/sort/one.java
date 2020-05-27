package datastructure.sort;

import java.util.Arrays;

public class one {
    /**
     * 冒泡排序
     */
    public static void main(String[] args) {
        int []array = {2,3,6,1,8,3};
        for (int i = 0;i<array.length;i++){ //控制循环次数
            for (int j= 0;j<array.length-i-1;j++){ //控制排序次数
                if (array[j+1] < array[j]){
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
        }
        Arrays.stream(array).forEach(value -> System.out.print(value+"\t"));
    }
}
