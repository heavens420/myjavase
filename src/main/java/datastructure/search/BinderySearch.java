package datastructure.search;

import java.security.PublicKey;
import java.util.Arrays;

/**
 * 用二分查找法 查找已经排序的 数组
 *
 */
public class BinderySearch {
    public static void main(String[] args) {
//        int[] arr = {1,2,3,4,5,6,7,10,20,90};
        int[] arr = {34,65,65,65,65,65,99};

        int target = 65; //要查找的目标值
        System.out.println(find(arr, target));
    }

    public static int find(int[] arr,int target){
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;

        while (left < right){
            mid = left + (right - left)/2;
            if (target < arr[mid]){
                right = mid - 1;
            }
            else if (target > arr[mid]){
                left = mid + 1;
            }else {
                return mid ;//返回匹配到的索引值
            }
        }
            return -1;//找不到 返回 -1
    }
}
