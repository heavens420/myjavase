package com.zlx.GOF.factory.abstractfactorymethod2;

public class WindowsFactory implements Factory{
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new WindowsCheckBox();
    }
}
