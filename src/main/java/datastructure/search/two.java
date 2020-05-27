package datastructure.search;

/**
 * 插值查找
 */
public class two {
    public static void main(String[] args) {
        int[] arr = {3,5,7,9,12,34,54};
        System.out.println(find(arr,120));
    }

    public static int find(int[] arr,int target){
        int left = 0;
        int right = arr.length-1;
        int mid = 0;
        while (left < right && target >= arr[0] && target <= arr[arr.length-1]){//防止下标越界  因target参与中间值运算
            mid = left + (right - left)*(target - arr[left])/(arr[right] -arr[left]);
            if (arr[mid]>target){
                right = mid -1;
            }
            else if (arr[mid] < target){
                left = mid +1;
            }
            else {
                return mid;
            }

        }
        return -1;
    }
}
