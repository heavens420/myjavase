package com.zlx.JUC;

import java.util.Arrays;
import java.util.List;

public class ArrayTest {
    public static void main(String[] args) {
        int[] arr = new int[5];
        int[] array = new int[]{1,4,5,8568,54};
        Arrays.asList(1,2,3,4,5);
        Arrays.sort(array);
        int a1 = Arrays.binarySearch(array,3);
        int a2 = Arrays.binarySearch(array,5);
        int a3 = Arrays.binarySearch(arr,2,4,4);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
        System.out.println(Arrays.toString(array));


        int[] arrayCopy = Arrays.copyOf(array, array.length);
        System.out.println(Arrays.toString(arrayCopy));

        List list =  Arrays.asList(arr);
        List list2 = Arrays.asList(array);
//        Collections.addAll(list,list2);
        //list.addAll(list2);
        Integer[] integers = new Integer[list.size()];
        //list.toArray(integers);
        System.out.println();



        boolean b = Arrays.equals(arrayCopy,array);
        System.out.println(b);
    }
}
