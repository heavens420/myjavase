package com.zlx.GOF.flyweight;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式核心类
 * 棋子工厂 创建棋子
 */
public class ChessFlyWeight {

    static Map<String, Chess> map = new HashMap<String, Chess>();

    /**
     * 创建棋子 第一次直接创建 并保存第一次创建的对象 后面就直接取对象避免二次创建
     * @return
     */
    public static Chess createChess(String color) {
        Chess chess = map.get(color);
        if (chess == null) {
            if (color.equals("white")) {
                chess = new WhiteChess();
            } else if (color.equals("black")) {
                chess = new BlackChess();
            } else {
                throw new IllegalArgumentException("Invalid color");
            }
            map.put(color, chess);
        }
        return chess;
    }

}
