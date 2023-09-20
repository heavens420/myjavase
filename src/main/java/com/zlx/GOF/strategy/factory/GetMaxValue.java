package com.zlx.GOF.strategy.factory;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component("MAX")
public class GetMaxValue implements Strategy{
    @Override
    public Integer getValue(List<Integer> values) {
        return values.stream().max(Comparator.naturalOrder()).get();
    }
}
