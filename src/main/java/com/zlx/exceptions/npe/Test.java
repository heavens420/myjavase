package com.zlx.exceptions.npe;

import com.zlx.poi.DataModel;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author Zhao LongLong
 * @Date 2020/9/28
 * @Version 1.0
 * @Desc
 */
public class Test {
    public static void main(String[] args) {
//        NPE2();
//        NPE1();
//        NPE3();
//        NPE4();
//        NPE5();
        System.out.println(StringUtils.equals("1","2"));
    }

    public static void NPE1(){
        String s = null;
//        System.out.println(s.toString());
    }

    public static void NPE2(){
        DataModel dataModel = new DataModel();
        dataModel.setId(null);
        System.out.println(dataModel.toString());
    }

    public static void NPE3(){
        String s = null;
        System.out.println(s.equals(""));
    }

    public static void NPE4(){
        Long a = null;
        System.out.println(Long.toString(a));
    }

    public static void NPE5(){
        String s = null;
        System.out.println(s.length());
    }


}
