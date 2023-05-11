package com.zlx.GOF.flyweight;

/**
 * 颜色枚举类
 */
public enum Color {

    BLACK("黑色"), WHITE("白色");
    private String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
