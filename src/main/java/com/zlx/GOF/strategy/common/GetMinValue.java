package com.zlx.GOF.strategy.common;


import java.util.Comparator;
import java.util.List;

/**
 * 策略实现类
 * 获取最小值
 */
public class GetMinValue implements Strategy{
    @Override
    public Integer getValue(List<Integer> values) {
        return values.stream().min(Comparator.naturalOrder()).get();
    }
}
