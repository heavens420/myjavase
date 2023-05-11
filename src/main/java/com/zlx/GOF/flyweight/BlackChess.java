package com.zlx.GOF.flyweight;

public class BlackChess implements Chess{
    private final String color = "black";

    private final String shape = "circle";


    @Override
    public void drawPosition(int x, int y) {
        System.out.println(String.format("%s%s棋子置于（%d，%d）处", shape, color, x, y));
    }
}
