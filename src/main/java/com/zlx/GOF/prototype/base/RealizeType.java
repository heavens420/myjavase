package com.zlx.GOF.prototype.base;

/**
 * @Author Zhao LongLong
 * @Date 2020/8/25
 * @Version 1.0
 * @Desc 具体原型模式提供浅克隆和深克隆两种方式，重写Cloneable可实现浅克隆
 */
public class RealizeType implements Cloneable {
    public RealizeType(){
        System.out.println("具体原型创建成功");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        System.out.println("具体原型类复制成功");
        return (RealizeType)super.clone();
    }
}
