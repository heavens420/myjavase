package com.zlx.GOF.simplefactory;

//若该类只有方法没有属性 则该类也可以是接口 让其他类实现

public class Operation {
    private int numberA;
    private int numberB;

    public int getNumberA() {
        return numberA;
    }

    public void setNumberA(int numberA) {
        this.numberA = numberA;
    }

    public int getNumberB() {
        return numberB;
    }

    public void setNumberB(int numberB) {
        this.numberB = numberB;
    }

    //用于计算最终结果
    public int getResult(){
        return 0;
    }
}
