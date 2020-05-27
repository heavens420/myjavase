package datastructure.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 */

public class one {
    public static void main(String[] args) {
        int[] arr = {34,65,65,65,65,65,99};
        List list = find(arr,65);
        list.stream().forEach(s -> System.out.print(s+"\t"));
    }

    public static List<Integer> find(int[] arr,int target){
        int left = 0;
        int right = arr.length - 1;
        int mid =  0;
        List list = new ArrayList<Integer>();

        while (left < right){
            mid = (left + right)/2;
            if (target < arr[mid]){//在左边
                right = mid -1;
            }
            else if (target > arr[mid]){//在右边
                left = mid +1;
            }else {
                //return mid;//找到目标
//               list.add(mid);
//               left = mid+1;
//               right = arr.length-1;

                for (int i = mid; i >=0 ; i--) {
                    if (arr[i] == target){
                        list.add(i);
                    }else {
                        break;
                    }
                }

                for (int i = mid+1; i < arr.length-1; i++) {
                    if (arr[i] == target){
                        list.add(i);
                    }else {
                        break;
                    }
                }
                return list;//找到返回
            }
        }
        return new ArrayList<>();//未找到
    }
}
