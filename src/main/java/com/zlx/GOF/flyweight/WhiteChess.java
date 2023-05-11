package com.zlx.GOF.flyweight;

public class WhiteChess implements Chess{

    private final String color = "white";

    private final String shape = "circle";

    private int x;
    private int y;

    @Override
    public void drawPosition(int x, int y) {
        System.out.println(String.format("%s%s棋子置于（%d，%d）处", shape, color, x, y));
    }
}
