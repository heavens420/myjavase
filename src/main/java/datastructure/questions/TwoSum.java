package datastructure.questions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5};
        int target = 7;
        int result[] = test1(array, target);
        System.out.println(Arrays.toString(result));
    }


    public static int[] test1(int[] array, int target) {
        Map<Integer, Integer> map = new HashMap(8);
        for (int i = 0; i < array.length; i++) {
            int key = target - array[i];
            if (map.containsKey(key)) {
                return new int[]{map.get(key), i};
            }
            map.put(array[i], i);
        }
        return null;
    }
}
