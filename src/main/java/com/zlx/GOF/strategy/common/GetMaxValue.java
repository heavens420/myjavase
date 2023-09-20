package com.zlx.GOF.strategy.common;


import java.util.Comparator;
import java.util.List;

/**
 * 策略实现类
 * 获取最大值
 */
public class GetMaxValue implements Strategy{
    @Override
    public Integer getValue(List<Integer> values) {
        return values.stream().max(Integer::compareTo).get();
    }
}
