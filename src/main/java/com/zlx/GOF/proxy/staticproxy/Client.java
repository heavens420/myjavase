package com.zlx.GOF.proxy.staticproxy;

public class Client {

    public static void main(String[] args) {
        Star star = new RealStar();
        Star proxy = new ProxyStar(star);

        proxy.confer();
        proxy.signConcat();
        proxy.bookTicket();
        proxy.sing();
        proxy.collectMoney();
    }
}
