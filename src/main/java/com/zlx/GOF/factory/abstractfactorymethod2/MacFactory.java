package com.zlx.GOF.factory.abstractfactorymethod2;

public class MacFactory implements Factory{
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new MacCheckBox();
    }
}
