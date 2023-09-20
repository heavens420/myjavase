package com.zlx.GOF.strategy.enums;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 枚举+策略模式
 * 1 定义枚举类
 *  1.1 在枚举类中定义抽象方法
 *  1.2 在枚举类中实现抽象方法
 * 2 使用枚举类
 *
 * 另一种方式
 * 1 定义接口
 * 2 定义枚举类并实现接口
 * 3 在枚举类中定义常量决定执行具体哪个方法 同上述枚举类方式 只是这里实现的是接口方法 上述是实现枚举方法
 */

@Slf4j
public class Test {

    public static void main(String[] args) {
        List<Integer> values = new ArrayList<Integer>(Arrays.asList(534, 6, 234, 12, 757, 42, 3));
        String type = "MAX";
        ValueEnum valueEnum = ValueEnum.valueOf(type);
        Integer value = valueEnum.getValue(values);
        log.info("Value: {}", value);
    }
}