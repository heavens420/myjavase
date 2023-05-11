package com.zlx.GOF.proxy.staticproxy2;

public class Client {
    public static void main(String[] args) {
        lsLawSuit();
    }

    public static void zsLawSuit() {
        Lawyer lawyer = ProxyFactory.doIt(new ZhangSanLawSuit());
        lawyer.submit("借条在此");
        lawyer.define();
    }

    public static void lsLawSuit() {
        Lawyer lawyer = ProxyFactory.doIt(new LisiLawSuit());
        lawyer.submit("视频监控在此");
        lawyer.define();
    }
}
