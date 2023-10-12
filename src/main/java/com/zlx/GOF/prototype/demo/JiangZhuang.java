package com.zlx.GOF.prototype.demo;

import com.zlx.GOF.prototype.base.Another;
import com.zlx.GOF.prototype.base.RealizeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @Author Zhao LongLong
 * @Date 2020/8/25
 * @Version 1.0
 */
//@Data
@ToString
public class JiangZhuang implements Cloneable{

    private String name;
    private String info;
    private String college;

    //此类实现Cloneable接口 则该对象会被深拷贝 否则为浅拷贝 结果验证都是深拷贝
    private RealizeType realizeType;

    // another 没有实现cloneable接口
    private Another another;

    public JiangZhuang() {
    }

    public JiangZhuang(String name, String info, String college) {
        this.name = name;
        this.info = info;
        this.college = college;
        System.out.println("具体原 已创建");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public RealizeType getRealizeType() {
        return realizeType;
    }

    public void setRealizeType(RealizeType realizeType) {
        this.realizeType = realizeType;
    }

    public Another getAnother() {
        return another;
    }

    public void setAnother(Another another) {
        this.another = another;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
