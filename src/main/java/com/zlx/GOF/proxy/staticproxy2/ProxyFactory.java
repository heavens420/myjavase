package com.zlx.GOF.proxy.staticproxy2;

public class ProxyFactory {

    public static Lawyer doIt(LawSuit lawSuit) {
        return new Lawyer(lawSuit);
    }
}
