package com.zlx.GOF.factory.abstractfactorymethod2;


/**
 * 客户端代码 传什么类型接口 创建什么类型的对象
 */
public class Application {

    private final Button button;
    private final CheckBox checkBox;

    public Application(Factory factory) {
        this.button = factory.createButton();
        this.checkBox = factory.createCheckBox();
    }

    public void paint() {
        button.paint();
        checkBox.paint();
    }

}
