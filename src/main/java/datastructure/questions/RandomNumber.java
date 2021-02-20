package datastructure.questions;

import java.util.Arrays;

/**
 * 随机生成[2,20]的正整数，使得所有随机数之和为1000
 */
public class RandomNumber {
    public static void main(String[] args) {
        // 极端情况 数组长度最大为500
        int[] arr = new int[500];
        // 数组索引
        int i = 0;
        // 所有随机数之和
        int sum = 1000;
        // 不断生成随机数
        while (true){
            // 控制随机数范围
            int randomNumber = (int)(Math.random() * 18) + 2;
            // 保存生成的随机数,同时数组下标后移
            arr[i++] = randomNumber;
            // 1000 - 已生成的所有随机数的总和
            sum -= randomNumber;

            // sum ==0 则最后一位随机数加上之前生成的随机数之和恰好1000 此时结束循环 否则会再循环一次
            if (sum == 0){
                break;
            }
            // sum < 0说明生成的随机数之和大于1000 此时修改最后一个随机数的值 使得所有随机数之和为1000
            // 此处可能出现个问题,若前面所有随机数之和为999,则修改后的最后一个随机数将为1,此时不满足随机数规则
            // 为避免这种情况,只生成n-1个随机数 最后一个随机数由前面的随机数之和与1000做差取得即为sum值
//            if (sum < 0) { // 错误写法(若这样写,则是生成n个随机数,下面写法是生成n-1个随机数,n为总的随机数个数)
//                arr[i-1] = randomNumber + sum;
//            }
            if ( sum <= 20 && sum >= 2){
                arr[i] = sum;
                break;
            }
        }
        System.out.println(Arrays.toString(arr));
        // 随机数个数为i+1个 故需要i+1长度的数组保存
        int[] newArray = new int[i+1];
        System.arraycopy(arr,0,newArray,0,i+1);
        System.out.println(Arrays.toString(newArray));
    }
}
