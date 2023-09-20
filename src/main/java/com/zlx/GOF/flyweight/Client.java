package com.zlx.GOF.flyweight;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {
    public static void main(String[] args) {
        Chess whiteChess = ChessFlyWeight.createChess("white");
        whiteChess.drawPosition(1, 3);

        Chess blackChess = ChessFlyWeight.createChess("black");
        blackChess.drawPosition(3, 5);

        Chess white2Chess = ChessFlyWeight.createChess("white");
        white2Chess.drawPosition(3, 4);

        Chess black2Chess = ChessFlyWeight.createChess("black");
        black2Chess.drawPosition(6, 7);

        log.info("whiteChess:{}",whiteChess.hashCode());
        log.info("blackChess:{}",blackChess.hashCode());
        log.info("white2Chess:{}",white2Chess.hashCode());
        log.info("black2Chess:{}",black2Chess.hashCode());
    }
}
