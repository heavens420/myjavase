package com.zlx.GOF.strategy.common;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 常规的策略模式
 * 1 定义策略接口
 * 2 定义具体策略实现类
 * 3 构造Context接收策略实现类并封装方法调用实现类的具体方法
 * 4 使用Context
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        List<Integer> values = new ArrayList<Integer>(Arrays.asList(23,534,8,544,21,45));
        String type = "min";
        switch (type) {
            case "max":
                Context context = new Context(new GetMaxValue());
                Integer max = context.getValue(values);
                log.info("max: {}", max);
                break;
            case "min":
                Context context2 = new Context(new GetMinValue());
                Integer min = context2.getValue(values);
                log.info("min: {}", min);
                break;
            default:
                log.info("type 有误");
        }
    }
}
