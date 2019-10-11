package com.zlx.test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        List list = new ArrayList<Integer>(Arrays.asList(12,43,54,76,98,334));
        List list2 = new ArrayList<Integer>();
        Collections.addAll(list2,12,32,32,78,45,98,123);

        list2.addAll(list2.size(),list);
        Collections.addAll(list,list2);

        list.remove(2);

        int index = list.indexOf(12);//第一个匹配值

        int last = list2.lastIndexOf(32);//最后一个匹配值

        List sublist = list.subList(1,3);//截取[1,3)元素

        System.out.println(list2);
        System.out.println(list);
        System.out.println(index);
        System.out.println(last);
        System.out.println(sublist);
    }
}
