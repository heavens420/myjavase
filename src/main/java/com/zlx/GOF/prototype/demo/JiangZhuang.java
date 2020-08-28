package com.zlx.GOF.prototype.demo;

import com.zlx.GOF.prototype.base.RealizeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @Author Zhao LongLong
 * @Date 2020/8/25
 * @Version 1.0
 */
@Data
@ToString
public class JiangZhuang implements Cloneable{

    private String name;
    private String info;
    private String college;

    //此类实现Cloneable接口 则该对象会被深拷贝 否则为浅拷贝
    private RealizeType realizeType;

    public JiangZhuang(String name, String info, String college) {
        this.name = name;
        this.info = info;
        this.college = college;
        System.out.println("具体原 已创建");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return ((JiangZhuang) super.clone());
    }
}
