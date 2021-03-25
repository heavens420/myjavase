package com.zlx;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{2,5,8,23,54,22};
//      test2();
//        test1();
        findMax(arr);
    }


    public static void test2() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] strs = str.split(",");
        System.out.println(Arrays.toString(strs));
        int[] arr = new int[strs.length];
        for (int i = 0 ; i< strs.length ;i++){
            arr[i] = Integer.parseInt(strs[i]);
        }
        System.out.println(Arrays.toString(arr)+"oooooooo");
        int load = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length ; j++) {
                if (arr[i] > arr[j]) {
                    int temp = 0;
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));
        int count = 0;
        for (int k = 0; k< arr.length;k++){
            load -= arr[k];
            if (load < 0){
                break;
            }
            count++;
        }
        System.out.print(count);

    }

    public static void test1() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if (str.equals("")){
            System.out.println("");
            return;
        }
        String[] strs = str.split(",");
        int[] arr = new int[strs.length];
        for (int i = 0 ; i< strs.length;i++){
                arr[i] = Integer.parseInt(strs[i]);
        }
        System.out.println(Arrays.toString(arr));

        if (arr.length <= 3) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]);
            }
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length ; j++) {
                if (arr[i] > arr[j]) {
                    int temp = 0;
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        System.out.println(arr[0] + "" + arr[1] + arr[2] + "");
        System.out.println(Arrays.toString(arr));

    }

    public static void findMax(int[] arr){
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]){
                max = arr[i];
            }
        }
        System.out.println(max);
    }
}
