package com.zlx.GOF.strategy.factory;

import java.util.List;

/**
 * 策略接口
 */
public interface Strategy {
    Integer getValue(List<Integer> values);
}
