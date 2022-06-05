package com.zlx.generics.demo1;

import java.util.List;

public class Common {

    /**
     * 可传入参数 ：animal或其子类
     * @param animals
     * @return
     */
    public static int conunt(List<? extends Animal> animals) {
        int sum = 0;
        for (Animal animal : animals) {
            sum += animal.legs;
        }
        return sum;
    }
}
