package com.zlx.GOF.strategy.common;

import java.util.List;

/**
 *
 */
public class Context {
    private final Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public Integer getValue(List<Integer> values) {
        return strategy.getValue(values);
    }
}
