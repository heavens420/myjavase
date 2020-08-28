package com.zlx.GOF.prototype.demo;

/**
 * @Author Zhao LongLong
 * @Date 2020/8/25
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        JiangZhuang zhangsan = new JiangZhuang("张三","一等奖","北京小学");
        System.out.println(zhangsan);
        JiangZhuang lisi = ((JiangZhuang) zhangsan.clone());
        lisi.setName("李四");
        System.out.println(lisi);
    }
}
