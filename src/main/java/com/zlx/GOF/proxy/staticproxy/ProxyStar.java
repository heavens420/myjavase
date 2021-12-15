package com.zlx.GOF.proxy.staticproxy;

public class ProxyStar implements Star{

    private Star star;

    public ProxyStar(Star star) {
        this.star = star;
    }

    @Override
    public void confer() {
        star.confer();
        System.out.println("代理人 confer");
    }

    @Override
    public void signConcat() {
        star.signConcat();
        System.out.println("代理人 signConcat");
    }

    @Override
    public void bookTicket() {
        star.bookTicket();
        System.out.println("代理人 bookTicket");
    }

    @Override
    public void sing() {
        star.sing();
    }

    @Override
    public void collectMoney() {
        star.collectMoney();
        System.out.println("代理人 collectMoney");
    }
}
