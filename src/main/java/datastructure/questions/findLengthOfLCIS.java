package datastructure.questions;

public class findLengthOfLCIS {
    public static void main(String[] args) {
//        int[] array = {2,4,7,3,4,7,8,3,4,5,6,7,8};
        int[] array = {};
        int len = array.length == 0 ? 0 : 1;
        int maxLen = len;

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] < array[i+1]){
                len++;
            }else {
                len = 1;
            }
            if (maxLen < len){
                maxLen = len;
            }
        }
        System.out.println(maxLen);
    }
}
