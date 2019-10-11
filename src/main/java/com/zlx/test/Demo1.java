package com.zlx.test;

import javax.sound.midi.Soundbank;
import java.io.IOException;
/*
    冒泡排序：
    每一趟将最小的或最大的数依次找出来放在第一位或最后一位
 */
public class Demo1 {
    public static void main(String[] args) throws IOException {
        int[] array = {23,564,757,-432,534,63,-4,0};
        System.out.print("原数组： ");
        for (int num:array
             ) {
            System.out.print(num+" ");
        }
        System.out.println();
        System.out.print("排序后： ");
        maopao(array);
        for (int num:array
             ) {
            System.out.print(num+" ");
        }
    }

    public static void maopao(int []array){
        for (int i=0;i<array.length-1;i++){
            int temp=0;
            for (int j=array.length-1;j>i;j--){
                if (array[j]<array[j-1]){
                    temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
            }
        }
    }

}
