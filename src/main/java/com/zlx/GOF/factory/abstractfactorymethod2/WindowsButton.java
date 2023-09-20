package com.zlx.GOF.factory.abstractfactorymethod2;

public class WindowsButton implements Button {
    @Override
    public void paint() {
        System.out.println("window button");
    }
}
