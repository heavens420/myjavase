package com.zlx.GOF.strategy.factory;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component("MIN")
public class GetMinValue implements Strategy{
    @Override
    public Integer getValue(List<Integer> values) {
        return values.stream().min(Comparator.naturalOrder()).get();
    }
}
