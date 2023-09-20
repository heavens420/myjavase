package com.zlx.GOF.strategy.enums;

import java.util.Comparator;
import java.util.List;

/**
 * 策略枚举类
 */
public enum ValueEnum {

    MAX{
        @Override
        public Integer getValue(List<Integer> values) {
            return values.stream().max(Integer::compareTo).get();
        }
    },
    MIN {
        @Override
        public Integer getValue(List<Integer> values) {
            return values.stream().min(Comparator.naturalOrder()).get();
        }
    };

    public abstract Integer getValue(List<Integer> values);
}
