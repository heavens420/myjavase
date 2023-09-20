package com.zlx.GOF.factory.abstractfactorymethod2;

public class WindowsCheckBox implements CheckBox{
    @Override
    public void paint() {
        System.out.println("windows checkBox");
    }
}
