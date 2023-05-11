package com.zlx.GOF.facade;


/**
 * 外观模式封装多个系统或者复杂实现 并提供一个简单的接口供外部使用
 */
public class Client {
    public static void main(String[] args) {
        ReportFacade reportFacade = new ReportFacade();
        reportFacade.buy();
    }
}
