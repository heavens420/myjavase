package com.zlx.GOF.chainofresponsibility.demo1;

/**
 * 责任链模式
 */
public class Client {
    public static void main(String[] args) {
        GroupLeader groupLeader = new GroupLeader();
        Manager manager = new Manager();
        Cfo cfo = new Cfo();

        groupLeader.setNextHandler(manager);
        manager.setNextHandler(cfo);

        boolean result = groupLeader.handle(900);
        if (result) {
            System.out.println("感谢领导同意");
        } else {
            System.out.println("一堆垃圾领导 可真是个垃圾");
        }

    }
}
