package com.zlx.GOF.prototype.demo;

import com.zlx.GOF.prototype.base.Another;
import com.zlx.GOF.prototype.base.RealizeType;
import com.zlx.generics.demo2.A;

/**
 * @Author Zhao LongLong
 * @Date 2020/8/25
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        test2();
    }

    public static void test1() throws CloneNotSupportedException {
        JiangZhuang zhangsan = new JiangZhuang("张三", "一等奖", "北京小学");
        zhangsan.setAnother(new Another("Another222", "888"));
        System.out.println(zhangsan);
        JiangZhuang lisi = ((JiangZhuang) zhangsan.clone());
        // 强转后 对象已不相等 所以后续操作lisi 不会影响zhangsan
        System.out.println(lisi == zhangsan);
        lisi.setName("李四");
        lisi.setRealizeType(new RealizeType());
        lisi.setAnother(new Another("Another", "123"));
        System.out.println(lisi);
        System.out.println(zhangsan);
    }

    public static void test2() throws CloneNotSupportedException {
        JiangZhuang zhangsan = new JiangZhuang();
        zhangsan.setAnother(new Another("Another222", "888"));
        zhangsan.setRealizeType(new RealizeType());
        System.out.println("zhangsan：" + zhangsan);
        JiangZhuang clone = (JiangZhuang) zhangsan.clone();
        System.out.println("clone zhangsan:" + clone);
        boolean equal = zhangsan == clone;
        System.out.println("是否相等："+ equal);
        // 修改 未实现cloneable的引用对象
        clone.setAnother(new Another("Another", "122"));
        // 修改 已实现cloneable的引用对象
        clone.setRealizeType(new RealizeType());
        System.out.println("修改后的克隆对象：" + clone);
        System.out.println("原对象zhangsan：" + zhangsan);

    }
}
