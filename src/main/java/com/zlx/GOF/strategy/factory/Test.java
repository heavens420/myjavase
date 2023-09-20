package com.zlx.GOF.strategy.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 策略模式+手动工厂 (基于spring环境)
 *
 * 1 创建策略接口
 * 2 创建接口实现类并对指定实现类bean名称
 * 3 通过map的形式注入策略接口 从而获得bean的map集合
 * 4 通过前端传入的key从map中获取对应的策略实现类并执行策略方法
 */
@Slf4j
public class Test {

    @Autowired
    // 通过map的形式注入策略实现类 key为bean名称 value为对应实现类对象
    private Map<String,Strategy> strategy;

    public void doExecuteStrategy() {
        List<Integer> values = new ArrayList<Integer>(Arrays.asList(432,543,6,23,21));
        String type = "MAX";
        Integer value = strategy.get(type).getValue(values);
        log.info("value:{}", value);
    }
}
